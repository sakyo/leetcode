package com.leetcode.medium;

import com.leetcode.Solution;

/**
 * NextClosestTime
 * 给定一个形如 “HH:MM” 表示的时刻，利用当前出现过的数字构造下一个距离当前时间最近的时刻。每个出现数字都可以被无限次使用。
 * <p>
 * 你可以认为给定的字符串一定是合法的。例如，“01:34” 和 “12:09” 是合法的，“1:34” 和 “12:9” 是不合法的。
 * <p>
 *  
 * <p>
 * 样例 1:
 * <p>
 * 输入: "19:34"
 * 输出: "19:39"
 * 解释: 利用数字 1, 9, 3, 4 构造出来的最近时刻是 19:39，是 5 分钟之后。结果不是 19:33 因为这个时刻是 23 小时 59 分钟之后。
 *  
 * <p>
 * 样例 2:
 * <p>
 * 输入: "23:59"
 * 输出: "22:22"
 * 解释: 利用数字 2, 3, 5, 9 构造出来的最近时刻是 22:22。 答案一定是第二天的某一时刻，所以选择可构造的最小时刻。
 * <p>
 * 链接：https://leetcode-cn.com/problems/next-closest-time
 *
 * @author yangzhongwei
 * @date 2020-03-03 18:36
 */
public class NextClosestTime extends Solution {

    public String nextClosestTime(String time) {
        char[] nums = new char[4];
        nums[0] = time.charAt(0);
        nums[1] = time.charAt(1);
        nums[2] = time.charAt(3);
        nums[3] = time.charAt(4);
        char[] result = time.toCharArray();
        char min = find(nums, (char) ('0' - 1), '9');
        Character t;
        if ((t = find(nums, time.charAt(4), '9')) != null) {
            result[4] = t;
            return new String(result);
        } else {
            result[4] = min;
        }
        if ((t = find(nums, time.charAt(3), '5')) != null) {
            result[3] = t;
            return new String(result);
        } else {
            result[3] = min;
        }
        if ((t = find(nums, time.charAt(1), time.charAt(0) == '2' ? '4' : '9')) != null) {
            result[1] = t;
            return new String(result);
        } else {
            result[1] = min;
        }
        if ((t = find(nums, time.charAt(0), '2')) != null) {
            result[0] = t;
            return new String(result);
        } else {
            return new String(new char[]{min, min, ':', min, min});
        }
    }

    public Character find(char[] nums, char f, char max) {
        Character more = null;
        for (char c : nums) {
            if (c <= max && c > f && (more == null || more > c)) {
                more = c;
            }
        }
        return more;
    }

    @Override
    public void test() {
        System.out.println(nextClosestTime("19:34"));
    }
}
