package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * ShortestDistanceFromAllBuildings
 * <p>
 * 你是个房地产开发商，想要选择一片空地 建一栋大楼。你想把这栋大楼够造在一个距离周边设施都比较方便的地方，通过调研，你希望从它出发能在 最短的距离和 内抵达周边全部的建筑物。请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 你只能通过向上、下、左、右四个方向上移动。
 * <p>
 * 给你一个由 0、1 和 2 组成的二维网格，其中：
 * <p>
 * 0 代表你可以自由通过和选择建造的空地
 * 1 代表你无法通行的建筑物
 * 2 代表你无法通行的障碍物
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * <p>
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 输出：7
 * 解析：
 * 给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
 * 由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
 *  
 * <p>
 * 注意：
 * <p>
 * 题目数据保证至少存在一栋建筑物，如果无法按照上述规则返回建房地点，则请你返回 -1。
 * <p>
 * 链接：https://leetcode-cn.com/problems/shortest-distance-from-all-buildings
 *
 * @date 2020-08-13 19:23
 */
public class ShortestDistanceFromAllBuildings extends Solution {

    public int shortestDistance(int[][] grid) {
        // 存距离
        int[][] distance_grid = new int[grid.length][grid[0].length];
        // 存可达几个
        int[][] time_grid = new int[grid.length][grid[0].length];
        // 遍历建筑
        int building = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    building++;
                    compute(grid, distance_grid, time_grid, i, j, building);
                }
            }
        }
        // 遍历空地
        int result = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (time_grid[i][j] == building && (result == -1 || distance_grid[i][j] < result))
                result = distance_grid[i][j];
            }
        } return result;
    }

    public void compute(int[][] grid, int[][] distance_grid, int[][] time_grid, int m, int n, int building) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(m, n, 0));
        while (queue.size() != 0) {
            Node node = queue.poll();
            int i = node.i;
            int j = node.j;
            int step = node.step;
            if (step != 0) {
                distance_grid[i][j] += step;
                time_grid[i][j] += 1;
            }
            step++;
            if (i > 0 && grid[i - 1][j] != 1 && grid[i - 1][j] != 2 && grid[i - 1][j] != building + 2) {
                queue.offer(new Node(i - 1, j, step));
                grid[i - 1][j] = building + 2;
            }
            if (i < grid.length - 1 && grid[i + 1][j] != 1 && grid[i + 1][j] != 2 && grid[i + 1][j] != building + 2) {
                queue.offer(new Node(i + 1, j, step));
                grid[i + 1][j] = building + 2;
            }
            if (j > 0 && grid[i][j - 1] != 1 && grid[i][j - 1] != 2 && grid[i][j - 1] != building + 2) {
                queue.offer(new Node(i, j - 1, step));
                grid[i][j - 1] = building + 2;
            }
            if (j < grid[0].length - 1 && grid[i][j + 1] != 1 && grid[i][j + 1] != 2 && grid[i][j + 1] != building + 2) {
                queue.offer(new Node(i, j + 1, step));
                grid[i][j + 1] = building + 2;
            }
        }
    }

    class Node {

        int i;

        int j;

        int step;

        public Node(int i, int j, int step) {
            this.i = i;
            this.j = j;
            this.step = step;
        }
    }

    @Override
    public void test() {
        System.out.println(shortestDistance(convert("[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]", new int[][]{})));
    }

}
