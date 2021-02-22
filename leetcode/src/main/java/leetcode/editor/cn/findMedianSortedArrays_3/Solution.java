package leetcode.editor.cn.findMedianSortedArrays_3;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double res = 0;

        final int m = nums1.length;
        final int n = nums2.length;
        final int mn = m + n;

        List<Integer> ks = new ArrayList<>();
        if (mn % 2 == 1) {
            ks.add(mn / 2);
        } else {
            final int e = mn / 2;
            ks.add(e - 1);
            ks.add(e);
        }

        int[] num = new int[mn];
        int i = 0;
        int j = 0;
        int k = 0;

        final Integer maxK = ks.get(ks.size() - 1);

        while (k < maxK + 1) {
            if (i < m) {
                if (j < n) {
                    if (nums1[i] <= nums2[j]) {
                        num[k++] = nums1[i++];
                    } else {
                        num[k++] = nums2[j++];
                    }
                } else {
                    num[k++] = nums1[i++];
                }
            } else {
                num[k++] = nums2[j++];
            }
        }

        for (Integer e : ks) {
            res += num[e];
        }

        return res / ks.size();
    }

}