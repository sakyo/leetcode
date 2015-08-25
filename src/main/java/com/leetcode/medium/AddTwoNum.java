package com.leetcode.medium;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p/>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p/>
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNum {

    public static class Solution {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            long vaule1 = response(l1);
            long value2 = response(l2);
            long total = value2 + vaule1;
            if (vaule1 > value2) {
                show(l1, total);
                return l1;
            } else {
                show(l2, total);
                return l2;
            }

        }

        public long response(ListNode listNode) {
            long i = 1;
            long result = 0;
            while (listNode != null) {
                result = result + listNode.val * i;
                i = i * 10;
                listNode = listNode.next;
            }
            return result;
        }

        public void show(ListNode listNode, long result) {
            long i = 10;
            ListNode pre = null;
            while (listNode != null) {
                listNode.val = (int)(((result % i) * 10) / i);
                result = result - (listNode.val * i / 10);
                i = i * 10;
                pre = listNode;
                listNode = listNode.next;
            }
            if (result != 0 && pre != null) {
                pre.next = new ListNode(1);
            }
        }
    }

    public static class ListNode {

        int val;

        ListNode next;

        ListNode(int x) { val = x; }
    }
}
