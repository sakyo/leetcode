package com.leetcode.hard;

import com.leetcode.Solution;

/**
 * ReachingPoints
 * <p>
 * A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
 * <p>
 * Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
 * <p>
 * Examples:
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: True
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * <p>
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: False
 * <p>
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: True
 * <p>
 * Note:
 * <p>
 * sx, sy, tx, ty will all be integers in the range [1, 10^9].
 * <p>
 * https://leetcode.com/problems/reaching-points/
 */
public class ReachingPoints extends Solution {

    //Map<String, Boolean> cache = new HashMap<>();
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (tx == ty && ty == 0 && (sx != 0 || sy != 0))
            return false;
        while (sx <= tx && sy <= ty) {
            if (sx == tx && sy == ty)
                return true;
            if (tx > ty)
                tx = diff(tx, ty, sx);
            else if (tx < ty)
                ty = diff(ty, tx, sy);
            else {
                if (sx == 0 && sy == ty)
                    return true;
                if (sy == 0 && sx == tx)
                    return true;
                return false;
            }
        }
        return false;
    }

    public int diff(int big, int small, int back) {
        int r = big % small;
        if (r < back && big != back && r == back % small)
            r = back;
        if (r == 0)
            r = small;
        return r;
    }

    @Override
    public void test() {
        System.out.println(reachingPoints(1, 5, 19, 5));
    }
}
