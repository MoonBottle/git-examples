package leetcode.editor.cn.lengthOfLongestSubstring;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        final int strLength = s.length();
        Set<Character> occ = new HashSet<>();
        int res = 0;
        int start = 0;
        for (int i = 0; i < strLength; i++) {
            // 重复了，重新开始计算
            if (occ.contains(s.charAt(i))) {
                res = Math.max(res, occ.size());
                occ.clear();
                occ.add(s.charAt(start + 1));
                i = start = start + 1;
                continue;
            }
            occ.add(s.charAt(i));
        }

        res = Math.max(res, occ.size());

        return res;
    }


//    public int lengthOfLongestSubstring(String s) {
//        // 记录字符上一次出现的位置
//        int[] last = new int[128];
//        for(int i = 0; i < 128; i++) {
//            last[i] = -1;
//        }
//        int n = s.length();
//
//        int res = 0;
//        int start = 0; // 窗口开始位置
//        for(int i = 0; i < n; i++) {
//            int index = s.charAt(i);
//            start = Math.max(start, last[index] + 1);
//            res   = Math.max(res, i - start + 1);
//            last[index] = i;
//        }
//
//        return res;
//    }

}