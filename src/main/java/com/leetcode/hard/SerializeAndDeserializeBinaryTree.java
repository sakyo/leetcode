package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SerializeAndDeserializeBinaryTree
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Example:
 * <p>
 * You may serialize the following tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 * <p>
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeserializeBinaryTree {

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public static final char SPLIT = '_';

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        List<TreeNode> sources = new ArrayList<TreeNode>();
        sources.add(root);
        while (sources.size() > 0) {
            List<TreeNode> newsources = new ArrayList<TreeNode>();
            for (TreeNode node : sources) {
                sb.append(SPLIT);
                if (node != null) {
                    sb.append(node.val);
                    newsources.add(node.left);
                    newsources.add(node.right);
                }
            }
            sources = newsources;
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null)
            return null;
        StringTokenizerInter tokenizer = new StringTokenizerInter(data.toCharArray(), SPLIT);
        tokenizer.nextToken();
        TreeNode result = parse(tokenizer);
        List<TreeNode> source = new ArrayList<TreeNode>();
        source.add(result);
        while (source.size() > 0) {
            List<TreeNode> newsource = new ArrayList<TreeNode>();
            for (TreeNode node : source) {
                if (node != null) {
                    node.left = parse(tokenizer);
                    node.right = parse(tokenizer);
                    newsource.add(node.left);
                    newsource.add(node.right);
                }
            }
            source = newsource;
        }
        return result;
    }

    public TreeNode parse(StringTokenizerInter tokenizer) {
        String s = tokenizer.nextToken();
        if (s == null || s.length() == 0) {
            return null;
        }
        return new TreeNode(Integer.parseInt(s));
    }

    public class StringTokenizerInter {

        char[] strings;

        char split;

        int index = 0;

        public StringTokenizerInter(char[] strings, char split) {
            this.strings = strings;
            this.split = split;
        }

        public String nextToken() {
            int i = index;
            if (i >= strings.length)
                return null;
            for (; i < strings.length; i++)
                if (strings[i] == split)
                    break;
            String result = new String(strings, index, i - index);
            index = i + 1;
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new SerializeAndDeserializeBinaryTree().deserialize("_1_2_3___4_5"));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
