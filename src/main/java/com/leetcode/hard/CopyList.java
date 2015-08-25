package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copy List with Random Pointer
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * <p/>
 * Return a deep copy of the list.
 * <p/>
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyList {

    /**
     * Definition for singly-linked list with a random pointer.
     */
    static class RandomListNode {

        int label;

        RandomListNode next, random;

        RandomListNode(int x) { this.label = x; }
    }

    public static class Solution {

        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null)
                return null;
            int size = 1 << 10;
            MyHash hash = new MyHash(size);
            List<RandomListNode> nodes = new ArrayList<RandomListNode>();
            RandomListNode result = new RandomListNode(head.label);
            // only one node
            if (head.random != null)
                result.random = result;
            RandomListNode p1 = result;
            RandomListNode p2 = head;
            hash.put(p2, 0);
            nodes.add(p1);
            // first loop
            int i = 0;
            while (p2.next != null) {
                p1.next = new RandomListNode(p2.next.label);
                p1 = p1.next;
                p2 = p2.next;
                i++;
                hash.put(p2, i);
                nodes.add(p1);
            }
            // second loop
            p1 = result;
            p2 = head;
            do {
                if (p2.random != null)
                    p1.random = nodes.get(hash.get(p2.random));
                p1 = p1.next;
                p2 = p2.next;
            } while (p2 != null);
            return result;
        }

        public class MyHash {

            public class MyNode {

                public RandomListNode node;

                public int index;

                public MyNode(RandomListNode node, int index) {
                    this.node = node;
                    this.index = index;
                }
            }

            List<MyNode>[] tables;

            public MyHash(int length) {
                tables = new List[length];
            }

            public void put(RandomListNode node, int i) {
                int h = node.label;
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                h = h & (tables.length - 1);
                if (tables[h] == null) {
                    tables[h] = new ArrayList<MyNode>();
                }
                tables[h].add(new MyNode(node, i));
            }

            public int get(RandomListNode node) {
                int h = node.label;
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                h = h & (tables.length - 1);
                for (MyNode myNode : tables[h]) {
                    if (node == myNode.node)
                        return myNode.index;
                }
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        //System.out.println(new Solution().copyRandomList(input, new String[]{"oath", "pea", "eat", "rain"}).size());
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
