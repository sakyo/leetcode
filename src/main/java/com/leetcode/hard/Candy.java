package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 * <p>
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {

    public int candy(int[] ratings) {
        if(ratings.length ==1)
            return 1;
        int[] result = new int[ratings.length];
        for (int i = 0; i < result.length; i++)
            result[i] = 1;
        boolean iscomplete = false;
        while (!iscomplete) {
            iscomplete = true;
            for (int i = 1; i < result.length; i++) {
                if ((ratings[i] > ratings[i - 1]) && (result[i] <= result[i - 1])) {
                    result[i] = result[i - 1] + 1;
                    iscomplete = false;
                }
                if ((ratings[i] < ratings[i - 1]) && (result[i] >= result[i - 1])) {
                    result[i - 1] = result[i] + 1;
                    iscomplete = false;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < result.length; i++)
            sum += result[i];
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new Candy().candy(new int[]{1,2,2}));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
