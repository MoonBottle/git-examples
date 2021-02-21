package leetcode.editor.cn.findSubstring_30.lengthOfLongestSubstring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        int rk;
        final int oneWordLength = words[0].length();
        final int allWordLength = words.length * oneWordLength;
        List<Integer> res = new ArrayList<>();
        final int n = s.length();
        for (int lk = 0; lk + allWordLength <= n; lk++) {
            List<String> wordList = Stream.of(words).collect(Collectors.toList());
            rk = lk;
            while (rk < lk + allWordLength && wordList.remove(s.substring(rk, rk + oneWordLength))) {
                rk += oneWordLength;
            }
            if (wordList.size() == 0) {
                res.add(lk);
            }
        }

        return res;
    }

}