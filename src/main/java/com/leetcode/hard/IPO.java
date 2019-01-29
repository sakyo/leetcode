package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * IPO
 * <p>
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * <p>
 * You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * <p>
 * To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.
 * <p>
 * Example 1:
 * Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 * <p>
 * Output: 4
 * <p>
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 * Note:
 * You may assume all numbers in the input are non-negative integers.
 * The length of Profits array and Capital array will not exceed 50,000.
 * The answer is guaranteed to fit in a 32-bit signed integer.
 * <p>
 * https://leetcode.com/problems/ipo/
 */
public class IPO {

    public int findMaximizedCapital(int k, int w, int[] Profits, int[] Capital) {
        List<Node> pc = new ArrayList<Node>();
        for(int i = 0; i < Profits.length; i++){
            pc.add(new Node(Profits[i], Capital[i]));
        }
        return findMaximizedCapital(k, w, pc);
    }

    public int findMaximizedCapital(int k, int w, List<Node> pc) {
        PriorityQueue<Node> lows = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return -o1.profits + o2.profits;
            }
        });
        PriorityQueue<Node> highs = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return o1.capital - o2.capital;
            }
        });
        for(Node node : pc){
            if(node.capital <= w)
                lows.add(node);
            else
                highs.add(node);
        }
        for(int i= 0; i< k; i++){
            Node node  =  highs.peek();
            while (highs.peek() != null && highs.peek().capital <= w){
                lows.add(highs.poll());
            }
            if(lows.peek()!=null){
                w = w+ lows.poll().profits;
            }else {
                break;
            }
        }
        return w;
    }

    /****** 递归方法，效率低
    public int findMaximizedCapital(int k, int w, List<Node> pc) {
        if (k == 0 || pc.size() == 0)
            return w;
        int max = 0;
        for (int i = 0; i < pc.size(); i++) {
            if (w >= pc.get(i).capital) {
                List<Node> newpc = new ArrayList<Node>(pc);
                newpc.remove(i);
                int v = findMaximizedCapital(k, w+ pc.get(i).profits, newpc);
                if (v > max)
                    max = v;
            }
        }
        return max == 0 ? w : max;
    }
     ****/

    public static class Node {

        public int profits;

        public int capital;

        public Node(int profits, int capital) {
            this.profits = profits;
            this.capital = capital;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        System.out.println(new IPO().findMaximizedCapital(2, 0, new int[]{1,2,3},new int[]{0,1,2}));
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }
}
