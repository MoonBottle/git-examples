package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.ChildExcel;
import com.bottle.excelhelper.annotaion.Excel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ExcelHelper2 {

    public final static String EXCEL_SUFFIX_XLS = "xls";

    public final static String EXCEL_SUFFIX_XLSX = "xlsx";

    /**
     * 解析Excel文件
     *
     * @param file
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> parseExcel2Object(File file, Class<T> clazz, int dataBeginRowNumber) throws Exception {
        Workbook workbook;
        try {
            workbook = createWorkBook(getFileSuffix(file), new FileInputStream(file));
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Excel解析失败,请检查文件是否正确");
        }
        return getExcelData(workbook.getSheetAt(0), clazz, true, dataBeginRowNumber);
    }

    /**
     * 根据后缀创建excel
     *
     * @param suffix
     * @return
     * @throws Exception
     */
    static Workbook createWorkBook(String suffix, InputStream inputStream) throws Exception {

        if (EXCEL_SUFFIX_XLS.equals(suffix)) {
            return new HSSFWorkbook(inputStream);
        } else if (EXCEL_SUFFIX_XLSX.equals(suffix)) {
            return new XSSFWorkbook(inputStream);
        } else {
            throw new Exception("文件格式不正确");
        }
    }

    /**
     * 获取文件的后缀
     *
     * @param file
     * @return
     * @throws Exception
     */

    private static String getFileSuffix(File file) throws Exception {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
    }

    /**
     * 将导入的Excel表格转成需要的对象列表
     *
     * @param file
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> List<T> parseExcel2Object(MultipartFile file, Class<T> clazz, boolean requeireNonNullRow, int dataBeginRowNumber) throws Exception {
        Workbook workbook = createWorkBook(getFileSuffix(file), file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        return getExcelData(sheet, clazz, requeireNonNullRow, dataBeginRowNumber);
    }

    /**
     * 获取上传文件的后缀
     *
     * @param file
     * @return
     * @throws Exception
     */
    static String getFileSuffix(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("文件不存在");
        }
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    /**
     * 获取sheet页数据
     *
     * @param sheet sheet页
     * @param clazz 要转换的实体类
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> List<T> getExcelData(Sheet sheet, Class<T> clazz, boolean requiredNonNullRow, int dataBeginRowNumber) throws Exception {
        // 获取合并单元格
        List<CellRangeAddress> cellRangeAddressList = sheet.getMergedRegions();

        // 获取所有行
        List<Row> rowList = Stream.iterate(dataBeginRowNumber, n -> n + 1)
                .limit(sheet.getLastRowNum())
                .map(sheet::getRow)
                .collect(toList());

        if (requiredNonNullRow && rowList.stream().anyMatch(r -> Objects.isNull(r))) {
            throw new RuntimeException("Excel 不能存在没有数据的空行，请检查");
        }

        List<Node<T>> nodeList = parseExcel2Node(clazz, rowList, cellRangeAddressList);

        return toInstanceList(nodeList);
    }

    private static <T> List<Node<T>> parseExcel2Node(Class<T> clazz, List<Row> rowList, List<CellRangeAddress> cellRangeAddressList) throws IllegalAccessException, InstantiationException {

        HashMap<Class, NodeMeta> nodeMetaMap = new HashMap<>();
        collectNodeMeta(nodeMetaMap, clazz);

        List<Node<T>> nodeList = rowList.stream()
                .map(row -> mapRow2Node(row, clazz, nodeMetaMap, cellRangeAddressList, null))
                .collect(toList());

        calcPrimaryNode(nodeList);
        linkNodeList(nodeList.stream().map(n -> n).collect(toList()), nodeMetaMap);

        return nodeList;
    }

    static <T> Node<T> mapRow2Node(Row row, Class<T> clazz, Map<Class, NodeMeta> nodeMetaMap, List<CellRangeAddress> cellRangeAddressList, Node parentNode) {
        Node<T> node = new Node<>();
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        node.setClazz(clazz);
        node.setCellRangeAddressList(cellRangeAddressList);
        node.setInstance(instance);

        node.setRow(row);
        List<Cell> cellList = new ArrayList<>();
        node.setCellList(cellList);
        node.setParentNode(parentNode);

        NodeMeta nodeMeta = nodeMetaMap.get(clazz);
        for (FieldExcelTuple fieldExcelTuple : nodeMeta.getFieldExcelTupleList()) {
            Field field = fieldExcelTuple.field;
            Excel excel = fieldExcelTuple.excel;
            field.setAccessible(true);
            Cell cell = row.getCell(excel.column() - 1);

            if (cell == null) {
                cell = row.createCell(excel.column() - 1);
            }

            cellList.add(cell);
            try {
                // 修改 cell 类型为字符类型进行处理
                cell.setCellType(Cell.CELL_TYPE_STRING);
                field.set(instance, cell.getStringCellValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }

        node.setFirstRow(row.getRowNum());
        node.setFirstCol(cellList.get(0).getColumnIndex());
        node.setNodeMeta(nodeMeta);

        // 防止泛型对象参数转换导致内存地址变更
//        Node<T> pNode = node;
        nodeMeta.addNode(clazz, node);

        if (nodeMeta.getChildExcel() != null) {
            node.setChildNode(mapRow2Node(row, nodeMeta.getChildExcel().clazz(), nodeMetaMap, cellRangeAddressList, node));
        }
        return node;
    }

    static void collectNodeMeta(Map<Class, NodeMeta> nodeMetaMap, Class clazz) {
        NodeMeta nodeMeta = new NodeMeta();
        Map<Field, Excel> fieldMap = new HashMap<>();
        List<FieldExcelTuple> fieldExcelTupleList = new ArrayList<>();
        Arrays.stream(clazz.getDeclaredFields())
                .forEach(field -> {
                    Excel excel = AnnotationUtils.findAnnotation(field, Excel.class);
                    if (excel != null) {
                        fieldMap.put(field, excel);
                        fieldExcelTupleList.add(new FieldExcelTuple(field, excel));
                    }

                    ChildExcel childExcel = AnnotationUtils.findAnnotation(field, ChildExcel.class);
                    if (childExcel != null) {
                        nodeMeta.setChildField(field);
                        nodeMeta.setChildExcel(childExcel);
                    }
                });

        nodeMeta.setFieldMap(fieldMap);
        nodeMeta.setFieldExcelTupleList(fieldExcelTupleList);
        nodeMeta.sortFieldExcelTupleList();
        nodeMetaMap.put(clazz, nodeMeta);

        if (nodeMeta.getChildExcel() != null) {
            collectNodeMeta(nodeMetaMap, nodeMeta.getChildExcel().clazz());
        }
    }

    @Deprecated
    static void calcPrimaryNode(HashMap<Class, NodeMeta> nodeMetaMap) {
//        List<Node> childrenNodeList = nodeList.stream()
//                .map(node -> node.getChildNode())
//                .collect(toList());
//
//        if (childrenNodeList.stream().anyMatch(Objects::isNull)) return;
//        nodeList.forEach(node -> node.calcPrimaryNode(nodeMetaMap.get(node.getClazz())));
//
//        calcPrimaryNode(childrenNodeList, nodeMetaMap);

        nodeMetaMap.forEach((clazz, nodeMeta) -> {
            nodeMeta.getClassNodeMap().forEach((c, nodeList) -> {
                nodeList.forEach(node -> node.calcPrimaryNode(nodeMetaMap.get(node.getClazz())));
            });
        });
    }

    static <T> void calcPrimaryNode(List<Node<T>> nodeList) {
        nodeList.parallelStream().forEach((Node node) -> {
            while (node.childNode != null) {
                node.calcPrimaryNode();
                node = node.childNode;
            }
        });
    }

    private static void linkNodeList(List<Node> nodeList) {
        nodeList.forEach(node -> {
            while (node.getChildNode() != null) {
                Node childNode = node.getChildNode();
                childNode.getParentNode().getPrimaryNode().addChildInstance(childNode.getInstance());
                node = childNode;
            }
        });
    }

    @Deprecated
    private static void linkNodeList(List<Node> nodeList, HashMap<Class, NodeMeta> nodeMetaMap) {
        List<Node> childrenNodeList = nodeList.stream()
                .map(node -> node.getChildNode())
                .collect(toList());

        if (childrenNodeList.stream().anyMatch(Objects::isNull)) return;

        nodeList.forEach(node -> {
            List<Object> childrenList = childrenNodeList.stream()
                    .filter(childNode -> {
                        return childNode.getParentNode().getPrimaryNode() == node.getPrimaryNode()
                                && childNode == childNode.getPrimaryNode();
                    }).map(Node::getInstance).collect(toList());
            node.getPrimaryNode().setChildrenInstanceList(childrenList, nodeMetaMap.get(node.clazz));
        });

        linkNodeList(childrenNodeList);
    }

    private static <T> List<T> toInstanceList(List<Node<T>> nodeList) {
        List<T> instanceList = nodeList.parallelStream()
                .map(Node::getPrimaryNode)
                .distinct()
                .map(Node::getInstance)
                .collect(toList());
        return instanceList;
    }

}
