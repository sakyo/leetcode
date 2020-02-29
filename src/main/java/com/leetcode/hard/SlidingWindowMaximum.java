package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Sliding Window Maximum
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 * <p>
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * @date 2019-12-16 14:36
 */
public class SlidingWindowMaximum extends Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0)
            return new int[]{};
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        k = Math.min(nums.length, k);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++)
            queue.offer(-nums[i]);
        result[0] = -queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.offer(-nums[i]);
            queue.remove(-nums[i - k]);
            result[i - k + 1] = -queue.peek();
        }
        return result;
    }

    public int[] max(int[] nums, int k) {
        if (nums.length == 0 || k == 0)
            return new int[]{};
        Deque<Integer> deque = new LinkedList<>();
        k = Math.min(nums.length, k);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (deque.size() > 0) {
                if (nums[deque.getLast()] <= nums[i])
                    deque.removeLast();
                else
                    break;
            }
            deque.addLast(i);
            if (i >= k && deque.getFirst() == i - k)
                deque.removeFirst();
            if (i >= k - 1)
                result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }

    @Override
    public void test() {
        System.out.println(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        System.out.println(max(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }
}
