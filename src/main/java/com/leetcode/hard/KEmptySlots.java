package com.leetcode.hard;

import com.leetcode.Solution;

/**
 * KEmptySlots
 * <p>
 * 花园里有 N 个花盆，每个花盆里都有一朵花。这 N 朵花会在 N 天内依次开放，每天有且仅有一朵花会开放并且会一直盛开下去。
 * <p>
 * 给定一个数组 flowers 包含从 1 到 N 的数字，每个数字表示在那一天开放的花所在的花盆编号。
 * <p>
 * 例如， flowers[i] = x 表示在第 i 天盛开的花在第 x 个花盆中，i 和 x 都在 1 到 N 的范围内。
 * <p>
 * 给你一个整数 k，请你输出在哪一天恰好有两朵盛开的花，他们中间间隔了 k 朵花并且都没有开放。
 * <p>
 * 如果不存在，输出 -1。
 * <p>
 *  
 * <p>
 * 样例 1:
 * <p>
 * 输入:
 * flowers: [1,3,2]
 * k: 1
 * 输出: 2
 * 解释: 在第二天，第一朵和第三朵花都盛开了。
 *  
 * <p>
 * 样例 2:
 * <p>
 * 输入:
 * flowers: [1,2,3]
 * k: 1
 * 输出: -1
 *  
 * <p>
 * 注释 :
 * <p>
 * 给定的数组范围是 [1, 20000]。
 *  
 * <p>
 * 链接：https://leetcode-cn.com/problems/k-empty-slots
 *
 * @author yangzhongwei
 * @date 2020-02-29 15:34
 */
public class KEmptySlots extends Solution {

    public int kEmptySlots1(int[] bulbs, int K) {
        if (bulbs.length < 2 || K++ < 0)
            return -1;
        boolean[] flowers = new boolean[bulbs.length+1];
        flowers[bulbs[0]] = true;
        for (int i = 1; i < bulbs.length; i++) {
            flowers[bulbs[i]] = true;
            if (check(flowers, bulbs[i], K))
                return i+1;
        }
        return -1;
    }

    public boolean check(boolean[] cache, int i, int k) {
        if (i >= k + 1 && cache[i - k] == true) {
            boolean result = true;
            for (int t = i - 1; t > i - k; t--) {
                if (cache[t]) {
                    result = false;
                    break;
                }
            }
            if (result)
                return result;
        }
        if (i + k <= cache.length - 1 && cache[i + k] == true) {
            boolean result = true;
            for (int t = i + 1; t < i + k; t++) {
                if (cache[t]) {
                    result = false;
                    break;
                }
            }
            if (result)
                return result;
        }
        return false;
    }

    @Override
    public void test() {
        System.out.println(kEmptySlots1(new int[]{1,3,2},1));
    }
}
