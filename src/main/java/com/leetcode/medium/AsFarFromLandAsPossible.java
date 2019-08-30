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
        int[][] cache_lu = new int[grid.length][grid.length];
        int[][] cache_ru = new int[grid.length][grid.length];
        int[][] cache_ld = new int[grid.length][grid.length];
        int max = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int tmp = find(i, j, cache_lu, grid, 1);
                tmp = Math.min(find(i, j, cache_ru, grid, 2), tmp);
                tmp = Math.min(find(i, j, cache_rd, grid, 3), tmp);
                tmp = Math.min(find(i, j, cache_ld, grid, 4), tmp);
                if (tmp > max)
                    max = tmp;
            }
        }
        if (max > 200 || max == 0)
            return -1;
        return max;
    }

    public int find(int i, int j, int[][] cache, int[][] grid, int direct) {
        if (cache[i][j] != 0)
            return cache[i][j];
        if (grid[i][j] == 1)
            return 0;
        int l = 500, r = 500, u = 500, d = 500; // 上下左右
        if (direct == 1 || direct == 4) {
            if (j != 0)
                l = find(i, j - 1, cache, grid, direct);
        }
        if (direct == 1 || direct == 2) {
            if (i != 0)
                u = find(i - 1, j, cache, grid, direct);
        }
        if (direct == 2 || direct == 3) {
            if (j != grid.length - 1)
                r = find(i, j + 1, cache, grid, direct);
        }
        if (direct == 3 || direct == 4) {
            if (i != grid.length - 1)
                d = find(i + 1, j, cache, grid, direct);
        }
        int min = Math.min(l, r);
        min = Math.min(min, u);
        min = Math.min(min, d);
        cache[i][j] = min + 1;
        return cache[i][j];
    }

    @Override
    public void test() {
        System.out.println(maxDistance(convert("[[0,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,1,0,1,0,0,0,1],[1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,1,1,0,1,0,0,0,1,1,1,0,1,1,0,1],[1,1,1,0,1,0,1,1,0,0,1,1,1,0,1,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0],[0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,1,1,1,0,1,0,1,0,1,0,0,1],[1,1,0,1,0,0,1,1,1,0,1,1,0,0,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0],[1,1,1,0,0,1,1,1,1,1,1,1,0,0,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,0],[1,1,1,1,0,1,1,0,0,0,1,1,1,0,0,1,1,1,0,0,0,1,0,0,1,0,1,1,0,0],[1,0,1,0,0,1,1,0,0,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,0,1],[0,0,1,0,1,0,1,1,1,1,1,1,0,0,0,0,0,1,0,1,1,1,0,1,0,0,1,1,0,1],[0,0,0,0,1,1,0,1,1,0,1,0,0,1,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1],[1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,1,0,1,1,0,1,0,0,1,1,0,1,0],[0,0,1,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1],[0,1,1,1,0,0,0,1,1,0,0,1,1,0,1,1,0,1,0,0,0,0,0,0,0,0,1,1,1,1],[0,1,0,1,0,0,0,1,1,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,1,1,1,1],[0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,1,0,0,0,1,1,0,0,1,1,1,0,1,1],[0,0,1,0,0,1,0,0,1,0,1,0,0,0,1,1,0,1,1,1,0,0,1,0,1,0,1,1,1,1],[1,1,0,0,1,0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,0,0,1,0,0,1,1,0],[1,1,1,0,1,1,0,0,0,0,0,1,1,0,0,1,0,0,1,1,1,0,1,1,0,1,1,0,0,1],[1,1,1,1,0,0,0,1,0,0,1,0,1,1,1,1,0,0,0,0,1,1,0,1,0,0,1,1,0,1],[1,0,0,0,1,1,0,0,1,1,0,0,0,1,1,0,0,1,0,0,1,0,0,1,0,1,0,0,0,1],[1,0,0,0,1,0,1,0,0,1,1,0,1,1,0,1,1,0,0,0,1,0,1,1,1,0,1,0,1,1],[0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,1,0,0,1,0,0,0,1,1,1,0,0,1,1,0],[0,0,1,0,0,0,1,1,0,0,1,0,0,0,1,1,0,0,0,0,1,1,1,1,1,1,0,0,1,1],[0,1,0,1,0,0,0,0,1,1,0,1,0,1,0,0,1,1,1,1,0,1,0,0,0,0,0,0,0,1],[1,0,1,1,0,0,0,1,1,0,1,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0],[0,1,0,0,0,1,1,1,1,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0],[0,0,1,1,1,0,0,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1,0,1,1,0,1,1,0,1],[0,0,1,1,1,0,0,0,0,0,0,1,1,0,0,1,0,0,1,1,0,0,1,0,0,0,1,0,1,0],[1,0,0,1,0,0,1,0,0,1,0,0,1,1,0,1,1,1,0,1,0,0,1,0,0,0,1,0,0,0],[0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0,0,1,1,0,0]]", new int[][]{})));
    }
}
