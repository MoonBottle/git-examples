package leetcode.editor.cn.isToeplitzMatrix_766;

public class Main {

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[][] matrix = new int[][]{
                {36, 59, 71, 15, 26, 82, 87},
                {56, 36, 59, 71, 15, 26, 82},
                {15, 0, 36, 59, 71, 15, 26}
        };
        System.out.println(solution.isToeplitzMatrix(matrix));
    }
}
