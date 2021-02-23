package leetcode.editor.cn.maxSatisfied;

import org.apache.commons.lang3.time.StopWatch;

public class Main {

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[] customers = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(solution.maxSatisfied(customers, grumpy, X));
        stopWatch.stop();
        System.out.println(stopWatch.toString());

        stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(solution.maxSatisfied2(customers, grumpy, X));
        stopWatch.stop();
        System.out.println(stopWatch.toString());
    }
}
