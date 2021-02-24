package leetcode.editor.cn.flipAndInvertImage;

public class Solution {

    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int[][] B = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = A[i][n - j - 1] ^ 1;
            }
        }

        return B;
    }

}