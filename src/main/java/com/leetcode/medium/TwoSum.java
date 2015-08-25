package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * <p/>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * <p/>
 * You may assume that each input would have exactly one solution.
 * <p/>
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * <p/>
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

    public static class Solution {

        public Map<Integer, Integer> hashes = new HashMap<Integer, Integer>();

        public int[] twoSum(int[] nums, int target) {
            return addFirst(nums, target);
        }

        public int[] addFirst(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                if (hashes.containsKey(nums[i])) {
                    return result(hashes.get(nums[i]) + 1, i + 1);
                }
                hashes.put(target - nums[i], i);
            }
            return null;
        }

        public int[] result(int first, int second) {
            int[] result = new int[2];
            result[0] = first;
            result[1] = second;
            return result;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)[0]);
    }
}
