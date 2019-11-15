package com.leetcode.medium;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * NextPermutation
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation extends Solution {

    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        int pos = nums.length - 2;
        while (pos >= 0) {
            if (nums[pos] < nums[pos + 1]) {
                insert(pos, nums);
                break;
            }
            pos--;
        }
        pos++;
        //reverse
        for (int i = pos; i <= (pos + nums.length - 1) / 2; i++) {
            int t = nums[i];
            nums[i] = nums[nums.length - 1 + pos - i];
            nums[nums.length - 1 + pos - i] = t;
        }
    }

    public void insert(int pos, int[] nums) {
        if (pos < 0)
            return;
        int ori = nums[pos];
        for (int i = pos + 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i] <= ori) {
                nums[pos] = nums[i - 1];
                nums[i - 1] = ori;
                break;
            }
        }
    }

    @Override
    public void test() {
        int[] tmp = new int[]{2, 3, 1};
        nextPermutation(tmp);
        System.out.println(Arrays.asList(tmp));
    }
}
