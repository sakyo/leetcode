package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p/>
 * Examples:
 * [2,3,4] , the median is 3
 * <p/>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p/>
 * Design a data structure that supports the following two operations:
 * <p/>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * <p/>
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 * <p/>
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianFinder {

    // 长度
    private int big_size = 0;

    // 数据
    private int[] big_data = new int[20000];

    // 长度
    private int small_size = 0;

    // 数据
    private int[] small_data = new int[20000];

    // Adds a number into the data structure.
    public void addNum(int num) {
        offer(num, true);
        offer(-poll(true), false);
        if (big_size < small_size)
            offer(-poll(false), true);
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (big_size > small_size)
            return big_data[0];
        else
            return (big_data[0] - small_data[0]) / 2.0;
    }

    private void offer(int i, boolean isbigger) {
        if (isbigger) {
            int k = big_size++;
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                if (big_data[parent] < i)
                    break;
                big_data[k] = big_data[parent];
                k = parent;
            }
            big_data[k] = i;
        } else {
            int k = small_size++;
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                if (small_data[parent] < i)
                    break;
                small_data[k] = small_data[parent];
                k = parent;
            }
            small_data[k] = i;
        }
    }

    private int poll(boolean isbigger) {
        if (isbigger) {
            --big_size;
            return poll(big_data, big_size);
        } else {
            --small_size;
            return poll(small_data, small_size);
        }
    }

    private int poll(int data[], int size) {
        int last = data[size];
        int e = data[0];
        int pos = 0;
        int half = size >>> 1;
        while (pos < half) {
            int child = (pos << 1) + 1;
            int right = child + 1;
            if (right < size && data[child] > data[right])
                child = right;
            if (last <= data[child])
                break;
            data[pos] = data[child];
            pos = child;
        }
        data[pos] = last;
        return e;
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        MedianFinder mf = new MedianFinder();
        for (int i = 0; i < 10000; i++) {
            mf.addNum(1);
            mf.addNum(2);
            mf.addNum(i);
            mf.findMedian();
        }
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }

}
