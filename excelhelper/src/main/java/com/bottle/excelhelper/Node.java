package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.ChildExcel;
import com.bottle.excelhelper.annotaion.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

/**
 * 所有节点
 *
 * @param <T>
 */
@Data
class Node<T> {

    Class<T> clazz;

    T instance;

    List<Object> childrenInstanceList;

    List<CellRangeAddress> cellRangeAddressList;

    Node<?> childNode;

    Stream<Node> childNodeStream;

    Node parentNode;

    Row row;

    List<Cell> cellList;

    int firstRow;

    int firstCol;

    Node<T> primaryNode;

    NodeMeta nodeMeta;

    public void calcPrimaryNode() {
        calcPrimaryNode(nodeMeta);
    }

    void calcPrimaryNode(NodeMeta nodeMeta) {
        List<Node> nodeList = nodeMeta.getClassNodeMap().get(clazz);
        primaryNode = this;
        this.cellRangeAddressList.stream()
                .filter(this::inCellRangeAddress)
                .findFirst()
                .ifPresent(r -> primaryNode = nodeList.get(r.getFirstRow() - 1));
    }

    private boolean inCellRangeAddress(CellRangeAddress c) {
        return c.getFirstRow() <= firstRow
                && c.getLastRow() >= firstRow
                && c.getFirstColumn() <= firstCol
                && c.getLastColumn() >= firstCol;
    }

    @Deprecated
    void setChildrenInstanceList(List<Object> childrenInstanceList, NodeMeta nodeMeta) {
        Field childField = nodeMeta.getChildField();
        if (childField == null)
            return;
        childField.setAccessible(true);

        try {
            if (childField.get(instance) == null) {
                childField.set(instance, childrenInstanceList);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        childField.setAccessible(false);
    }

    void initChildrenInstanceList(Field childField) {
        List<Object> childrenInstanceList = new ArrayList<>();
        childField.setAccessible(true);

        try {
            childField.set(instance, childrenInstanceList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        childField.setAccessible(false);
        this.childrenInstanceList = childrenInstanceList;
    }

    public void addChildInstance(Object instance) {
        Field childField = nodeMeta.getChildField();
        Objects.requireNonNull(childField);

        if (childrenInstanceList == null) {
            initChildrenInstanceList(childField);
        }

        childrenInstanceList.add(instance);
    }

    /**
     * distinct 方法
     * 只比较内存地址
     *
     * @param o
     * @return
     * @see ExcelHelper2#toInstanceList(List)
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public String toString() {
        return "Node{" +
                "clazz=" + clazz +
                ", instance=" + instance +
                ", cellRangeAddressList=" + cellRangeAddressList +
                ", childNode=" + childNode +
                ", row=" + row +
                ", cellList=" + cellList +
                ", firstRow=" + firstRow +
                ", firstCol=" + firstCol +
                '}';
    }

}


/**
 * 节点元数据
 */
@Data
class NodeMeta {

    @Deprecated
    Map<Field, Excel> fieldMap;

    List<FieldExcelTuple> fieldExcelTupleList = new ArrayList<>();

    LinkedHashMap<Class, List<Node>> classNodeMap = new LinkedHashMap<>();

    Field childField;

    ChildExcel childExcel;

    void sortFieldExcelTupleList() {
        // 按照 excel column 升序排序
        fieldExcelTupleList.sort((a, b) -> {
            return a.excel.column() - b.excel.column();
        });
    }

    void addNode(Class clazz, Node node) {
        List<Node> nodeList = classNodeMap.computeIfAbsent(clazz, k -> new ArrayList<>());
        nodeList.add(node);
    }

}

@AllArgsConstructor
class FieldExcelTuple {
    final Field field;
    final Excel excel;
}
