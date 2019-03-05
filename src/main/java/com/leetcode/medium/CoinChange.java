package com.leetcode.medium;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CoinChange
 * <p>
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount];
        return coinChange(coins, amount, cache);
    }

    public int coinChange(int[] coins, int amount, int[] cache) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;
        if (cache[amount-1] != 0)
            return cache[amount-1];
        int minresult = -1;
        for (int c : coins) {
            int j = coinChange(coins, amount - c, cache);
            if (j != -1 && (minresult > (j + 1) || minresult == -1))
                minresult = j + 1;
        }
        cache[amount-1] = minresult;
        return minresult;
    }


    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new CoinChange().coinChange(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
