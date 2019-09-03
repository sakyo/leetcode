package com.leetcode.medium;

import com.leetcode.Solution;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * HIndex
 * <p>
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * Example:
 * <p>
 * Input: citations = hi
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * <p>
 * https://leetcode.com/problems/h-index/
 */
public class HIndex extends Solution {

    public int hIndex(int[] citations) {
        TreeMap<Integer, Integer> cache = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int c : citations) {
            Integer old = cache.get(c);
            if (old == null)
                old = 1;
            else
                old++;
            cache.put(c, old);
        }
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            if (total + entry.getValue() == entry.getKey())
                return entry.getKey();
            if (total + entry.getValue() > entry.getKey())
                return total == 0 ? entry.getKey() : Math.max(total,entry.getKey());
            total += entry.getValue();
        }
        return citations.length;
    }

    @Override
    public void test() {
        System.out.println(hIndex(new int[]{3,0,6,1,5}));
    }
}
