package leetcode.editor.cn.flipAndInvertImage;

public class Solution {

    public int[][] flipAndInvertImage(int[][] A) {
        final int n = A.length;
        final int c = n / 2 + n % 2;
        int b;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                b = A[i][j];
                A[i][j] = A[i][n - j - 1] ^ 1;
                A[i][n - j - 1] = b ^ 1;
            }
        }

        return A;
    }

}