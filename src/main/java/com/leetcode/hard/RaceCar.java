package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RaceCar
 * Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
 * <p>
 * Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
 * <p>
 * When you get an instruction "A", your car does the following: position += speed, speed *= 2.
 * <p>
 * When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
 * <p>
 * For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
 * <p>
 * Now for some target position, say the length of the shortest sequence of instructions to get there.
 * <p>
 * Example 1:
 * Input:
 * target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3.
 * Example 2:
 * Input:
 * target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0->1->3->7->7->6.
 * <p>
 * Note:
 * <p>
 * 1 <= target <= 10000.
 * <p>
 * https://leetcode.com/problems/race-car/
 */
public class RaceCar {

    int[] cache = new int[1000];

    int[] mincache = new int[10000];

    public int racecar(int target) {
        if (target < 2)
            return target;
        int pos = 0;
        for (int i = 1; i < cache.length; i++) {
            if (cache[i] == 0)
                cache[i] = cache[i - 1] + (1 << (i - 1));
            if (cache[i] == target)
                return i;
            if (cache[i] > target) {
                pos = i;
                break;
            }
        }
        int leftcount = pos + 1 + racecarWithCache(target - cache[pos - 1]);
        int rightcount = pos + 1 + racecarWithCache(cache[pos] - target);
        for (int i = 1, le = target - cache[pos - 1] + cache[i]; le < target && i<3; i++, le = target - cache[pos - 1] + cache[i]) {
            if (le != (cache[pos] - target)) {
                int leftcount_1 = pos + 1 + racecarWithCache(le) + i;
                rightcount = Math.min(rightcount, leftcount_1);
            }
        }
        return Math.min(leftcount, rightcount);
    }

    public int racecarWithCache(int target) {
        if (mincache[target] == 0)
            mincache[target] = racecar(target);
        return mincache[target];
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new RaceCar().racecar(43));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
