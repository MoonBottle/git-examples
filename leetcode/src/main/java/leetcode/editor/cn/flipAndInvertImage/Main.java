package leetcode.editor.cn.flipAndInvertImage;


public class Main {

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final int[][] A = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
        System.out.println(solution.flipAndInvertImage(A));
    }
}
