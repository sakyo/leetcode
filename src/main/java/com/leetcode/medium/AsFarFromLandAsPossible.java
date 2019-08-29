package com.leetcode.medium;

import com.leetcode.Solution;

/**
 * AsFarFromLandAsPossible
 * <p>
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 * <p>
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * <p>
 * If no land or water exists in the grid, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation:
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation:
 * The cell (2, 2) is as far as possible from all the land with distance 4.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 * <p>
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 */
public class AsFarFromLandAsPossible extends Solution {

    public int maxDistance(int[][] grid) {
        int[][] cache_rd = new int[grid.length][grid.length];

    }

    // 往右下找最小值
    public int findrd(int i, int j, int[][] cache_rd, int[][] grid) {
        if (cache_rd[i][j] != 0)
            return cache_rd[i][j];
        if (grid[i][j] == 1)
            return 0;
        int r;
        if (j == grid.length - 1)
            r = 500;
        else
            r = findrd(i, j + 1, cache_rd, grid);
        int d;
        if (i == grid.length - 1)
            d = 500;
        else
            d = findrd(i + 1, j, cache_rd, grid);
        int result = Math.min(r, d);
        cache_rd[i][j] = result + 1;
        return cache_rd[i][j];
    }

    // 往左上找最小值
    public int findlu(int i, int j, int[][] cache_lu, int[][] grid) {
    }

    @Override
    public void test() {
    }
}
