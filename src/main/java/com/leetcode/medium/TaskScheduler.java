package com.leetcode.medium;

import com.leetcode.Solution;

/**
 * Task Scheduler
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * <p>
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 * <p>
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 * <p>
 * https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler extends Solution {

    public int leastInterval(char[] tasks, int n) {
        int[] m = new int[26];
        int max = 0;
        int max_count = 1;
        for (char t : tasks) {
            int v = ++m[t - 'A'];
            if (v > max) {
                max_count = 1;
                max = v;
            } else if (v == max) {
                max_count++;
            }
        }
        int result = (max - 1) * (n + 1) + max_count;
        if (result < tasks.length)
            result = tasks.length;
        return result;
    }

    @Override
    public void test() {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }
}
