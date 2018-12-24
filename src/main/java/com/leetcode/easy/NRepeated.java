package com.leetcode.easy;

/**
 * In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
 *
 * Return the element repeated N times.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,1,2,5,3,2]
 * Output: 2
 * Example 3:
 *
 * Input: [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 *
 * Note:
 *
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length is even
 *
 * @author yangzhongwei
 * @date 2018-12-23 15:43
 */
public class NRepeated {

    public int repeatedNTimes(int[] A) {
        int[] cache= new int[10000];
        for(int a: A){
            if(cache[a] != 0)
                return a;
            cache[a] = 1;
        }
        return 0;
    }
}
