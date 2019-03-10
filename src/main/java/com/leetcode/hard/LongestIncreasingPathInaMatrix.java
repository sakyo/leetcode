package com.leetcode.hard;

import com.leetcode.Solution;

/**
 * LongestIncreasingPathInaMatrix
 * <p>
 * Given an integer matrix, find the length of the longest increasing path.
 * <p>
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Example 1:
 * <p>
 * Input: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * Input: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * <p>
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInaMatrix extends Solution {

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return 0;
        int result = 1;
        int size_y = matrix.length;
        int size_x = matrix[0].length;
        int[] cache = new int[size_y * size_x];
        for (int i = 0; i < size_y; i++)
            for (int j = 0; j < size_x; j++) {
                int value = longestIncreasingPath(matrix, size_x, size_y, i, j, cache);
                if (value > result)
                    result = value;
            }
        return result;
    }

    public int longestIncreasingPath(int[][] matrix, int size_x, int size_y, int i, int j, int[] cache) {
        if (cache[i * size_x + j] != 0)
            return cache[i * size_x + j];
        int result = 1;
        int value = matrix[i][j];
        if (j > 0) {
            int left = matrix[i][j - 1];
            if (left > value) {
                int temp = longestIncreasingPath(matrix, size_x, size_y, i, j - 1, cache);
                if (temp >= result)
                    result = temp + 1;
            }
        }
        if (j < size_x - 1) {
            int left = matrix[i][j + 1];
            if (left > value) {
                int temp = longestIncreasingPath(matrix, size_x, size_y, i, j + 1, cache);
                if (temp >= result)
                    result = temp + 1;
            }
        }
        if (i > 0) {
            int left = matrix[i - 1][j];
            if (left > value) {
                int temp = longestIncreasingPath(matrix, size_x, size_y, i - 1, j, cache);
                if (temp >= result)
                    result = temp + 1;
            }
        }
        if (i < size_y - 1) {
            int left = matrix[i + 1][j];
            if (left > value) {
                int temp = longestIncreasingPath(matrix, size_x, size_y, i + 1, j, cache);
                if (temp >= result)
                    result = temp + 1;
            }
        }
        return cache[i * size_x + j] = result;
    }

    @Override
    public void test() {
        System.out.println(getClass());
    }
}
