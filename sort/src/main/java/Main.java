import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Department> originalList = new ArrayList<>();
        originalList.add(new Department("7", "国内采购部", "5"));
        originalList.add(new Department("8", "北上广采购部", "7"));
        originalList.add(new Department("9", "沿海采购部", "7"));
        originalList.add(new Department("2", "国外采购部", "5"));
        originalList.add(new Department("10", "欧美采购部", "2"));
        originalList.add(new Department("11", "澳洲采购部", "2"));
        originalList.add(new Department("3", "国内销售部", "6"));
        originalList.add(new Department("4", "国外销售部", "6"));
        originalList.add(new Department("1", "运营部", null));
        originalList.add(new Department("5", "采购部", "1"));
        originalList.add(new Department("6", "销售部", "1"));

        System.out.println(originalList);
//        List<Department> resultList = DepartmentSort.sort(originalList);
//        List<Department> resultList2 = DepartmentSort.sort2(originalList);
//        System.out.println(resultList);
        List<Department> resultList2 = DepartmentSort2.sort(originalList);
        System.out.println(resultList2);
    }
}
