package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.List;

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
        List<Node> nodes = new ArrayList<>(); // 所有节点
        int result = 1;
        int size_y = matrix.length;
        int size_x = matrix[0].length;
        // 建立有向图
        int index = 0;
        for (int i = 0; i < size_y; i++)
            for (int j = 0; j < size_x; j++) {
                nodes.add(new Node());
                if (j != 0) {
                    if (matrix[i][j - 1] > matrix[i][j])
                        nodes.get(index).nexts.add(nodes.get(index - 1));
                    else if (matrix[i][j - 1] < matrix[i][j])
                        nodes.get(index - 1).nexts.add(nodes.get(index));
                }
                if (i != 0) {
                    if (matrix[i - 1][j] > matrix[i][j])
                        nodes.get(index).nexts.add(nodes.get(index - size_x));
                    else if (matrix[i - 1][j] < matrix[i][j])
                        nodes.get(index - size_x).nexts.add(nodes.get(index));
                }
                index++;
            }
        // 深度优先计算
        for (Node node : nodes) {
            int dp = dp(node);
            if (dp > result)
                result = dp;
        }
        return result;
    }

    public int dp(Node node) {
        if (node.length != 0)
            return node.length;
        int max = 1;
        for (Node next : node.nexts) {
            int dp = dp(next);
            if (dp >= max)
                max = dp + 1;
        }
        node.length = max;
        return max;
    }

    public static class Node {

        List<Node> nexts = new ArrayList<>();

        int length;
    }

    @Override
    public void test() {
        System.out.println(longestIncreasingPath(new int[][]{new int[]{9,9,4},new int[]{6,6,8},new int[]{2,1,1}}));
    }
}
