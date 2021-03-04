import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentSort {

    private List<Department> deptList;

    private List<Department> resultList = new ArrayList<>();

    public DepartmentSort(List<Department> deptList) {
        this.deptList = deptList;
    }

    public static List<Department> sort(List<Department> originalList) {
        return new DepartmentSort(originalList).sort();
    }

    public static List<Department> sort2(List<Department> originalList) {
        return new DepartmentSort(originalList).sort();
    }

    private List<Department> sort() {
        findChildren(new Department());
        return resultList;
    }

    private List<Department> sort2() {
        return findChildren2(new Department());
    }

    /**
     * 查询下级部门
     *
     * @param parentDept
     */
    private void findChildren(Department parentDept) {
        // 查找所有下级部门
        List<Department> childrenList = deptList.stream()
                .filter(d -> Objects.equals(parentDept.getId(), d.getParentId()))
                .collect(Collectors.toList());

        if (childrenList.isEmpty())
            /* 不存在下级部门，跳出递归 */
            return;
        else
            // 遍历所有下级部门，塞入下级部门 resultList 的同时，查找所有该下级部门对应的下级部门
            childrenList.forEach(d -> {
                resultList.add(d);
                findChildren(d);
            });
    }

/**
 * 查询下级部门
 *
 * @param parentDept
 */
private List<Department> findChildren2(Department parentDept) {
    // 查找所有下级部门
    List<Department> childrenList = deptList.stream()
            .filter(d -> Objects.equals(parentDept.getId(), d.getParentId()))
            .collect(Collectors.toList());

    List<Department> tempList = new ArrayList<>();
    // 遍历所有下级
    childrenList.forEach(d -> {
        tempList.add(d);
        tempList.addAll(findChildren2(d));
    });
    return tempList;
}

}
