package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p/>
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * <p/>
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * <p/>
 * Credits:
 * Special thanks to @Freezen for adding this problem and creating all test cases.
 * <p/>
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class StockIV {

    public static class Solution {

        public int maxProfit(int k, int[] prices) {
            // 出题人没节操
            if (prices.length == 0)
                return 0;
            int[] diff = getDiff(prices);
            List<Integer> profits = new ArrayList<Integer>();
            int low = prices[0];// 低谷价格
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] >= prices[i - 1]) {
                    if (i == prices.length - 1 || prices[i + 1] < prices[i]) {
                        int profit = prices[i] - low;
                        if (profits.size() == 0)
                            profits.add(profit);
                        else
                            for (int j = 0; j < profits.size(); j++) {
                                if (profit >= profits.get(j)) {
                                    profits.add(j, profit);
                                    break;
                                } else if (j == profits.size() - 1) {
                                    profits.add(j + 1, profit);
                                }
                            }
                    }
                } else {
                    if (i < prices.length - 1 && prices[i + 1] >= prices[i])
                        low = prices[i];
                }
            }
            int result = 0;
            for (Integer i : profits) {
                if (k == 0)
                    break;
                result = result + i;
                k--;
            }
            return result;
        }

        // 计算趋势
        public int[] getDiff(int[] prices) {
            if (prices.length < 2)
                return prices;
            int[] diffs = new int[prices.length - 1];
            diffs[0] = prices[1] - prices[0];
            int diffindex = 0;
            for (int i = 2; i < prices.length; i++) {
                int diff = prices[i] - prices[i - 1];
                if (diff == 0)
                    continue;
                if ((diff >= 0 && diffs[diffindex] >= 0 || diff < 0 && diffs[diffindex] < 0))
                    diffs[diffindex] = diffs[diffindex] + diff;
                else {
                    diffindex++;
                    diffs[diffindex] = diff;
                }
            }
            return Arrays.copyOf(diffs, diffindex + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new Solution().maxProfit(2, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
