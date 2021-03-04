import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepartmentSort2 {

    private List<Department> deptList;

    public DepartmentSort2(List<Department> deptList) {
        this.deptList = deptList;
    }

    public static List<Department> sort(List<Department> originalList) {
        return new DepartmentSort2(originalList).sort();
    }

    private List<Department> sort() {
        return findChildren(new Department())
                .collect(Collectors.toList());
    }

    /**
     * 查询下级部门
     *
     * @param parentDept
     */
    private Stream<Department> findChildren(Department parentDept) {
        return deptList.stream()
                .filter(d -> Objects.equals(parentDept.getId(), d.getParentId()))
                .flatMap(d -> Stream.concat(Stream.of(d), findChildren(d)));
    }

}
