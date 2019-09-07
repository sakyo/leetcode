package com.leetcode.hard;

import com.leetcode.Solution;

/**
 * PoorPigs
 * <p>
 * There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?
 * <p>
 * Answer this question, and write an algorithm for the general case.
 * <p>
 * General case:
 * <p>
 * If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.
 * <p>
 * Note:
 * <p>
 * A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
 * After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.
 * Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
 * <p>
 * https://leetcode.com/problems/poor-pigs/
 */
public class PoorPigs extends Solution {

    // N进制表示
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if(buckets == 1)
            return 0;
        long round = minutesToTest / minutesToDie + 1;
        long result = 1, total = round;
        while (total < buckets) {
            total = round * total;
            ++result;
        }
        return (int) result;
    }

    @Override
    public void test() {
        System.out.println(poorPigs(1000, 15, 60));
    }
}
