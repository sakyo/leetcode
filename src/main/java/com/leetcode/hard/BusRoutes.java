package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BusRoutes
 * <p>
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * <p>
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 * <p>
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Note:
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 * <p>
 * https://leetcode.com/problems/bus-routes/
 */
public class BusRoutes extends Solution {

    public Integer a = null;

    // 站和线路对应关系
    Map<Integer, List<Integer>> sites = new HashMap<>();

    // 线路
    Map<Integer, List<Integer>> lines = new HashMap<>();

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S==T)
            return 0;
        for (int i = 0; i < routes.length; i++) {
            lines.put(i, new ArrayList<>());
            for (int j : routes[i]) {
                if (!sites.containsKey(j))
                    sites.put(j, new ArrayList<>());
                sites.get(j).add(i);
                lines.get(i).add(j);
            }
        }
        if (!sites.containsKey(S) || !sites.containsKey(T))
            return -1;
        int min = -1;
        List<Integer> restliens = new ArrayList<>(lines.keySet());
        for (int l : sites.get(S)) {
            int result = numBusesToDestination(S, T, l, restliens);
            if (min == -1 || result < min)
                min = result;
        }
        return min;
    }

    public int numBusesToDestination(int S, int T, int line, List<Integer> restlines) {
        if (lines.get(line).contains(T))
            return 1;
        if (restlines.size() == 1)
            return -1;
        int min = -1;
        restlines.remove(new Integer(line));
        for (Integer site : lines.get(line)) {
            // 起始站和非换乘点
            if (site == S || sites.get(site).size() == 1)
                continue;
            for (Integer l : sites.get(site)) {
                if (!restlines.contains(l))
                    continue;
                int result = numBusesToDestination(site, T, l, restlines);
                if (min == -1 ||  min > result)
                    min = result;
            }
        }
        restlines.add(line);
        if (min == -1)
            return min;
        return min + 1;
    }

    @Override
    public void test() {
        int[][] a = convert("[[3,16,33,45,59,79,103,135],[3,35,39,54,56,78,96,101,120,132,146,148],[13,72,98],[37,70,107],[0,12,31,37,41,68,78,94,100,101,113,123],[11,32,52,85,135],[43,50,128],[0,13,49,51,53,55,60,65,66,80,82,87,92,99,112,118,120,125,128,131,137],[15,19,34,37,45,52,56,97,108,123,142],[7,9,20,28,29,33,34,38,43,46,47,48,53,59,65,72,74,80,88,92,110,111,113,119,135,140],[15,41,64,83],[7,13,26,31,57,85,101,108,110,115,119,124,149],[47,61,67,70,74,75,77,84,92,101,124,132,133,142,147],[0,2,5,6,12,18,34,37,47,58,77,98,99,109,112,131,135,149],[6,7,8,9,14,17,21,25,33,40,45,50,56,57,58,60,68,92,93,100,108,114,130,149],[7],[5,16,22,48,77,82,108,114,124],[34,71],[8,16,32,48,104,108,116,134,145],[3,10,16,19,35,45,64,74,89,101,116,149],[1,5,7,10,11,18,40,45,50,51,52,54,55,69,71,81,82,83,85,89,96,100,114,115,124,134,138,148],[0,2,3,5,6,9,15,52,64,103,108,114,146],[5,33,39,40,44,45,66,67,68,69,84,102,106,115,120,128,133],[17,26,49,50,55,58,60,65,88,90,102,121,126,130,137,139,144],[6,12,13,37,41,42,48,50,51,55,64,65,68,70,73,102,106,108,120,123,126,127,129,135,136,149],[6,7,12,33,37,41,47,53,54,80,107,121,126],[15,75,91,103,107,110,125,139,142,149],[18,24,30,52,61,64,75,79,85,95,100,103,105,111,128,129,142],[3,14,18,32,45,52,57,63,68,78,85,91,100,104,111,114,142],[4,7,11,20,21,31,32,33,48,61,62,65,66,73,80,92,93,97,99,108,112,116,136,139]]",new int[][]{});
                int b = numBusesToDestination(a, 85, 112);
        System.out.println(b);
    }
}
