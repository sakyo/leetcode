package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 * <p/>
 * For example:
 * <p/>
 * Given "aacecaaa", return "aaacecaaa".
 * <p/>
 * Given "abcd", return "dcbabcd".
 * <p/>
 * <p/>
 * <p/>
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {

    public static class Solution {

        private char[] words;

        public String shortestPalindrome(String s) {
            words = s.toCharArray();
            if (words.length < 2)
                return s;
            if (words.length == 2) {
                if (words[0] == words[1])
                    return s;
                else
                    return words[1] + s;
            }
            //cache = new Boolean[words.length][words.length];
            int count = complementCount();
            if (count == 0)
                return s;
            return generStr(count);
        }

        public int complementCount() {
            int start = words.length / 2 - 1; // 4,1;3,0
            if (words.length % 2 == 0) {
                if (palindrome2(start))
                    return 0;
                start = start - 1;
            }
            for (int i = start; i >= 0; i--) {
                if (palindrome1(i))
                    return words.length - (2 * i) - 3;
                if (palindrome2(i))
                    return words.length - (2 * i) - 2;
            }
            return words.length - 1;
        }

        //带中间文字
        public boolean palindrome1(int start) {
            for (int i = start; i >= 0; i--) {
                if (words[i] != words[start * 2 + 2 - i])
                    return false;
            }
            return true;
        }

        //不带中间文字
        public boolean palindrome2(int start) {
            for (int i = start; i >= 0; i--) {
                if (words[i] != words[start * 2 + 1 - i])
                    return false;
            }
            return true;
        }

        public String generStr(int count) {
            char[] result = new char[count + words.length];
            for (int i = 0; i < count; i++) {
                result[i] = words[words.length - 1 - i];
            }
            for (int i = 0; i < words.length; i++) {
                result[i + count] = words[i];
            }
            return new String(result);
        }

    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new Solution().shortestPalindrome("aabba"));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
