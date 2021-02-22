package leetcode.editor.cn.isToeplitzMatrix_766;

public class Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean res = true;
//        m n = 2
        for (int lj = -(m - 1); lj < n; lj++) {
            int i = lj + m - 1;
            int j = 0;
            int k = matrix[i][0];
            for (; j < n; j++) {
                res = k == matrix[i][i + j];
                j++;
            }

        }


        return res;
    }

}