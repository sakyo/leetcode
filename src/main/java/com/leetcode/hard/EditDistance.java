package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * <p/>
 * You have the following 3 operations permitted on a word:
 * <p/>
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * <p/>
 * https://leetcode.com/problems/edit-distance/
 */
public class EditDistance {

    public static class Solution {

        private char[] word1;

        private char[] word2;

        private Integer[][] cache;

        public int minDistance(String word1, String word2) {
            this.word1 = word1.toCharArray();
            this.word2 = word2.toCharArray();
            cache = new Integer[word1.length()][word2.length()];
            return minDistance(0, 0);
        }

        public int minDistance(int start1, int start2) {
            int result;
            if (start1 == word1.length)
                return word2.length - start2;
            if (start2 == word2.length)
                return word1.length - start1;
            if (cache[start1][start2] != null)
                return cache[start1][start2];
            if (word1[start1] == word2[start2]) {
                result = minDistance(start1 + 1, start2 + 1);
                cache[start1][start2] = result;
                return result;
            }
            int i = minDistance(start1 + 1, start2);
            int j = minDistance(start1, start2 + 1);
            int k = minDistance(start1 + 1, start2 + 1);
            result = i > j ? j + 1 : i + 1;
            if (result > k + 1)
                result = k + 1;
            cache[start1][start2] = result;
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new Solution().minDistance("a", "b"));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
