package com.leetcode.medium;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * WordSearch
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * <p>
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        List<Position>[] cache = new List['z' - 'A' + 1];
        int x = board.length;
        int y = board[0].length;
        // init cache
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (cache[board[i][j] - 'A'] == null)
                    cache[board[i][j] - 'A'] = new ArrayList<Position>();
                cache[board[i][j] - 'A'].add(new Position(i, j));
            }
        }
        return isNeighbor(cache, word, 0, null, x, y);
    }

    public boolean isNeighbor(List<Position>[] cache, String word, int index, Position p, int x, int y) {
        if (index >= word.length())
            return true;
        List<Position> nexts = cache[word.charAt(index) - 'A'];
        if (nexts == null || nexts.size() == 0)
            return false;
        for (int i = 0; i < nexts.size(); i++) {
            Position pos = nexts.get(i);
            if (isNeighbor(p, pos, x, y)) {
                pos.used = true;
                boolean result = isNeighbor(cache, word, index + 1, pos, x, y);
                pos.used = false;
                if (result)
                    return true;
            }
        }
        return false;
    }

    public boolean isNeighbor(Position p, Position n, int x, int y) {
        if (p == null)
            return true;
        if (n.used == true)
            return false;
        if (p.x == n.x) {
            return Math.abs(p.y - n.y) == 1 || Math.abs(p.y - n.y) == y - 1;
        }
        if (p.y == n.y) {
            return Math.abs(p.x - n.x) == 1 || Math.abs(p.x - n.x) == x - 1;
        }
        return false;
    }

    public class Position {

        int x;

        int y;

        boolean used = false;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        //[['F','Y','C','E','N','R','D'],['K','L','N','F','I','N','U'],['A','A','A','R','A','H','R'],['N','D','K','L','P','N','E'],['A','L','A','N','S','A','P'],['O','O','G','O','T','P','N'],['H','P','O','L','A','N','O']]
        //'POLAND'
        char[][] input = new char[][]{new char[]{'F','Y','C','E','N','R','D'},new char[]{'K','L','N','F','I','N','U'},new char[]{'A','A','A','R','A','H','R'},new char[]{'N','D','K','L','P','N','E'},new char[]{'A','L','A','N','S','A','P'},new char[]{'O','O','G','O','T','P','N'},new char[]{'H','P','O','L','A','N','O'},};
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new WordSearch().exist(input, "POLAND"));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
