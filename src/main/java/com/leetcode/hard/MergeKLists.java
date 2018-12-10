package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Subscribe to see which companies asked this question.
 */
public class MergeKLists {

    public static class ListNode {

        int val;

        ListNode next;

        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 4) {
            List<ListNode> arrays = new ArrayList<ListNode>();
            for(ListNode node: lists){
                arrays.add(node);
            }
            return mergeKLists2(arrays);
        }
        List<ListNode> queue = new ArrayList<ListNode>();
        for (ListNode list : lists)
            addheap(queue, list);
        ListNode result = popheap(queue);
        ListNode last = result;
        while (last != null){
            ListNode next;
            if(last.next == null)
                next = popheap(queue);
            else if (queue.size() >0){
                if(last.next.val <= queue.get(0).val)
                    next = last.next;
                else {
                    next = popheap(queue);
                    addheap(queue, last.next);
                }
            }else {
                next = last.next;
            }
            last.next = next;
            last = next;
        }
        return result;
    }

    public ListNode mergeKLists2(List<ListNode> lists) {
        int i = -1;
        int mini = -1;
        ListNode minNode = null;
        int minvalue = Integer.MAX_VALUE;
        Iterator<ListNode> iterable = lists.iterator();
        while (iterable.hasNext()){
            ListNode node = iterable.next();
            if(node == null){
                iterable.remove();
                continue;
            }
            i++;
            if(node.val < minvalue){
                minNode = node;
                minvalue = node.val;
                mini = i;
            }
        }
        if (minNode == null)
            return null;
        ListNode result = minNode;
         lists.set(mini, minNode.next);
        result.next = mergeKLists2(lists);
        return result;
    }

    public void addheap(List<ListNode> lists, ListNode node) {
        if (node == null)
            return;
        lists.add(node);
        int orival = node.val;
        ListNode orinext = node.next;
        int n = lists.size() - 1;
        int father = (n - 1) / 2;
        while (lists.get(father).val > orival && n != 0) {
            lists.get(n).val = lists.get(father).val;
            lists.get(n).next = lists.get(father).next;
            n = father;
            father = (n - 1) / 2;
        }
        lists.get(n).val = orival;
        lists.get(n).next = orinext;
    }

    public ListNode popheap(List<ListNode> lists) {
        if (lists.size() == 0)
            return null;
        ListNode result = lists.get(0);
        lists.set(0, lists.get(lists.size() - 1));
        lists.remove(lists.size() - 1);
        if (lists.size() == 0)
            return result;
        int orival = lists.get(0).val;
        ListNode orinext = lists.get(0).next;
        int i = 0;
        int son = i * 2 + 1;
        while (son < lists.size()) {
            if (son + 1 < lists.size() && lists.get(son + 1).val < lists.get(son).val)
                son++;
            if (orival <= lists.get(son).val)
                break;
            lists.get(i).val = lists.get(son).val;
            lists.get(i).next = lists.get(son).next;
            i = son;
            son = i * 2 + 1;
        }
        lists.get(i).val = orival;
        lists.get(i).next = orinext;
        return result;
    }

    public static ListNode build(int[] input, int i){
        if(i == input.length)
            return null;
        ListNode node = new ListNode(input[i]);
        node.next = build(input, i+1);
        return node;
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{-8,-7,-7,-5,1,1,3,4};
        int[] input2 = new int[]{-2};
        int[] input3 = new int[]{-10,-10,-7,0,1,3};
        int[] input4 = new int[]{2};
        List<ListNode> nodes = new ArrayList<ListNode>();
        nodes.add(build(input1, 0));
        nodes.add(build(input2, 0));
        nodes.add(build(input3, 0));
        //nodes.add(build(input4, 0));
                //new int{,,};
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new MergeKLists().mergeKLists(nodes.toArray(new ListNode[]{})));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}

