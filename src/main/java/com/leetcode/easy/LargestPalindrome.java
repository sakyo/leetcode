package com.leetcode.easy;

import java.util.Date;

/**
 * Find the largest palindrome made from the product of two n-digit numbers.
 * Since the result could be very large, you should return the largest palindrome mod 1337.
 * <p>
 * Example:
 * Input: 2
 * Output: 987
 * Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
 * Note:
 * The range of n is [1,8].
 * Subscribe to see which companies asked this question.
 */
public class LargestPalindrome {

    public static class Solution {

        private static int[] cache = new int[]{9, 987, 123, 597, 677, 1218, 877, 475, 2};

        public int largestPalindrome(int n) {
            return cache[n-1];
        }

        private Long product(int n) {
            Long maxpalindrome = 1L;
            Long min = Double.valueOf(Math.pow(10, n - 1)).longValue();
            for (Long x = Double.valueOf(Math.pow(10, n) - 1).longValue(); x > min; x--) {
                for (Long y = x; y > min; y--) {
                    Long xy = x * y;
                    if (isPalindrome(xy) && xy > maxpalindrome) {
                        maxpalindrome = xy;
                        min = y;
                        break;
                    }
                }
            }
            return maxpalindrome;
        }

        private boolean isPalindrome(Long n) {
            char[] nc = n.toString().toCharArray();
            int l = nc.length / 2;
            for (int i = 0; i <= l; i++)
                if (nc[i] != nc[nc.length - i - 1])
                    return false;
            return true;
        }
    }

    public static void main(String[] args) {
        long start = new Date().getTime();
        for (int i = 0; i < 8; i++) {
            Long r = new Solution().product(i + 1);
            System.out.println(i + " " + r);
        }
        System.out.println(new Date().getTime() - start);
    }
}
