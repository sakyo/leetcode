package com.leetcode.medium;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * <p/>
 * For example:
 * <p/>
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * <p/>
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 * <p/>
 * Subscribe to see which companies asked this question
 * <p/>
 * https://leetcode.com/problems/single-number-iii/
 */
public class SingleNumberIII {

    public static class Solution {
        public int[] singleNumber(int[] nums) {
            int[] result = new int[2];
            int xor = nums[0];
            for(int i = 1; i<nums.length; i++)
                xor = xor ^ nums[i];
            xor = ((xor ^ (xor-1)) + 1) >>1; // xor & -xor
            for(int i = 0; i<nums.length; i++)
                if((xor & nums[i]) ==0)
                    result[0] = result[0] ^ nums[i];
                else
                    result[1] = result[1] ^ nums[i];
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        System.out.println(new Solution().singleNumber(new int[]{1, 2, 1, 3, 2, 5})[0]);
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }
}
