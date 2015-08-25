package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p/>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p/>
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * <p/>
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * <p/>
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {

    public static class Solution {

        public List<String> findWords(char[][] board, String[] words) {
            buildboard(board);
            List<String> result = new ArrayList<String>();
            for (String word : words) {
                if (findinboard(word.toCharArray()) && !result.contains(word))
                    result.add(word);
                initboard();
            }
            return result;
        }

        boolean findinboard(char[] chars) {
            if (cache[chars[0] - 'a'] == null)
                return false;
            for (Node node : cache[chars[0] - 'a']) {
                node.used = true;
                if (findinboard(node, chars, 1))
                    return true;
                node.used = false;
            }
            return false;
        }

        boolean findinboard(Node start, char[] words, int index) {
            if (index == words.length)
                return true;
            if (!start.up.used && words[index] == start.up.c) {
                start.up.used = true;
                if (findinboard(start.up, words, index + 1))
                    return true;
                start.up.used = false;
            }
            if (!start.right.used && words[index] == start.right.c) {
                start.right.used = true;
                if (findinboard(start.right, words, index + 1))
                    return true;
                start.right.used = false;
            }
            if (!start.down.used && words[index] == start.down.c) {
                start.down.used = true;
                if (findinboard(start.down, words, index + 1))
                    return true;
                start.down.used = false;
            }
            if (!start.left.used && words[index] == start.left.c) {
                start.left.used = true;
                if (findinboard(start.left, words, index + 1))
                    return true;
                start.left.used = false;
            }
            return false;
        }

        Node[] board;

        List<Node>[] cache = new List[26];

        void initboard() {
            for (Node node : board)
                node.used = false;
        }

        void buildboard(char[][] chars) {
            board = new Node[chars.length * chars[0].length];
            for (int i = 0; i < board.length; i++)
                board[i] = new Node();
            int i = 0;
            for (int m = 0; m < chars.length; m++) {
                for (int n = 0; n < chars[0].length; n++) {
                    if (cache[chars[m][n] - 'a'] == null)
                        cache[chars[m][n] - 'a'] = new ArrayList<Node>();
                    cache[chars[m][n] - 'a'].add(board[i]);
                    board[i].c = chars[m][n];
                    if (m - 1 >= 0)
                        board[i].up = board[i - chars[0].length];
                    else
                        board[i].up = new Node();
                    if (n + 1 < chars[0].length)
                        board[i].right = board[i + 1];
                    else
                        board[i].right = new Node();
                    if (m + 1 < chars.length)
                        board[i].down = board[i + chars[0].length];
                    else
                        board[i].down = new Node();
                    if (n - 1 >= 0)
                        board[i].left = board[i - 1];
                    else
                        board[i].left = new Node();
                    i++;
                }
            }
        }

        class Node {

            Node up;

            Node down;

            Node left;

            Node right;

            boolean used = false;

            char c = 0;
        }

    }

    public static void main(String[] args) {
        char[][] input = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        //char[][] input = new char[][]{{'a', 'b'}, {'c', 'd'}};
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new Solution().findWords(input, new String[]{"oath","pea","eat","rain"}).size());
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
