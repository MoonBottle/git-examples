package leetcode.editor.cn.maxSatisfied;

public class Solution {

    /**
     * 找窗口为3的，找到生气😠条件下人数最多的位置
     * lk = 0，rk = lk + x ，[lk, min(lk + x, n)) 位置上，如果 grumpy[i] == 1, sum(customers[i])
     * 最终满意人数 = 正常情况满意人数 + 使用技能挽回的人数总和
     * res = happy + save
     *
     * @param customers 每分钟内的顾客数
     * @param grumpy    每分钟内老板是否生气
     * @param X         最长保持冷静的时间
     * @return 感到满意的最大客户数量
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int total = 0;

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }

        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }

        int maxIncrease = increase;
        for (int i = X; i < n; i++) {
            increase = increase + customers[i] * grumpy[i] - customers[i - X] * grumpy[i - X];
            maxIncrease = Math.max(maxIncrease, increase);
        }

        return total + maxIncrease;
    }

    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int total = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        int maxIncrease = increase;
        for (int i = X; i < n; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }
}