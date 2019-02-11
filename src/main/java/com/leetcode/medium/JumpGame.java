package com.leetcode.medium;

/**
 * JumpGame
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * https://leetcode.com/problems/jump-game/
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        for (int i = nums[nums.length - 1] != 0 ? nums.length - 1 : nums.length - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                boolean can_jump = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[j] > i - j) {
                        can_jump = true;
                        i = j;
                        break;
                    }
                }
                if (!can_jump)
                    return false;
            }
        }
        return true;
    }
}
