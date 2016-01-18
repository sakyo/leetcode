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
    private int size = 0;

    // 数据
    private int[] data = new int[60002];

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (size == 0)
            data[0] = num;
        else
            add(insert(0, size - 1, num), num);
        size++;
    }

    // Returns the median of current data stream
    public double findMedian() {
        int pos = size / 2;
        if (size % 2 == 0)
            return (data[pos] + data[pos - 1]) / 2.0;
        else
            return data[pos];
    }

    public int insert(int start, int end, int num) {
        if (start >= end)
            return num > data[start] ? start + 1 : start;
        int pos = (start + end) / 2;
        if (num == data[pos])
            return pos;
        if (num < data[pos])
            return insert(start, pos - 1, num);
        return insert(pos + 1, end, num);
    }

    private void add(int pos, int num) {
        if (pos == size) {
            data[size] = num;
        } else {
            System.arraycopy(data, pos, data, pos + 1,
                    size - pos);
            data[pos] = num;
        }
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
