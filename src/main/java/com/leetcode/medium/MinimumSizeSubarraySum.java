package com.leetcode.medium;

import com.leetcode.Solution;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p/>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p/>
 * click to show more practice.
 * <p/>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * <p/>
 * Credits:
 * Special thanks to @Freezen for adding this problem and creating all test cases.
 * <p/>
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum{

    public int minSubArrayLen(int s, int[] nums) {
        if (s == 0)
            return 0;
        int start = 0, end, total = 0, count = 0;
        for (end = 0; end < nums.length; end++) {
            total += nums[end];
            if (total >= s) {
                count = end+1;
                break;
            }
        }
        if (total < s)
            return 0;
        total = total-nums[end];
        while (end < nums.length) {
            total = total + nums[end];
            for (; start < end; start++) {
                if (total - nums[start] >= s) {
                    count = Math.min(count, end - start);
                    total -= nums[start];
                } else
                    break;
            }
            end++;
        }
        return count;
    }

}
