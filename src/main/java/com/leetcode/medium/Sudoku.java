package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p/>
 * Empty cells are indicated by the character '.'.
 * <p/>
 * You may assume that there will be only one unique solution.
 * <p/>
 * <p/>
 * A sudoku puzzle...
 * <p/>
 * ...and its solution numbers marked in red.
 * <p/>
 * https://leetcode.com/problems/sudoku-solver/
 */
public class Sudoku {

    public static class Solution {

        public static final int SIZE = 9;

        public static final char EMPTY = '.';

        public static final boolean[] TEMPLATE = new boolean[SIZE];

        public void iniTemplate() {
            for (int i = 0; i < TEMPLATE.length; i++) {
                TEMPLATE[i] = false;
            }
        }

        public void solveSudoku(char[][] board) {
            solveSudoku(board, 0);
        }

        public boolean solveSudoku(char[][] board, int step) {
            if (step >= SIZE * SIZE)
                return true;
            int row = step / SIZE;
            int range = step % SIZE;
            if (board[row][range] != EMPTY)
                return solveSudoku(board, step + 1);
            List<Character> nextchars = Character(board, row, range);
            if (nextchars == null || nextchars.size() == 0)
                return false;
            for (char nextchar : nextchars) {
                board[row][range] = nextchar;
                if (solveSudoku(board, step + 1))
                    return true;
                else {
                    board[row][range] = EMPTY;
                }
            }
            return false;
        }

        // 这里可是存下来回溯
        public List<Character> Character(char[][] board, int row, int range) {
            iniTemplate();
            for (int i = 0; i < SIZE; i++) {
                if (board[row][i] != EMPTY)
                    TEMPLATE[board[row][i] - '1'] = true;
            }
            for (int j = 0; j < SIZE; j++) {
                if (board[j][range] != EMPTY)
                    TEMPLATE[board[j][range] - '1'] = true;
            }
            for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++)
                for (int j = range / 3 * 3; j < range / 3 * 3 + 3; j++)
                    if (board[i][j] != EMPTY)
                        TEMPLATE[board[i][j] - '1'] = true;
            List<Character> result = new ArrayList<Character>();
            for (int m = 0; m < TEMPLATE.length; m++) {
                if (!TEMPLATE[m])
                    result.add((char) (m + '1'));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] input = new String[]{"5.9748...", "78.......", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.", "........6", "...2759.."};
        char[][] inputchars = new char[9][9];
        for (int i = 0; i < 9; i++) {
            inputchars[i] = input[i].toCharArray();
        }
        new Solution().solveSudoku(inputchars);
        for (int i = 0; i < 9; i++) {
            input[i] = new String(inputchars[i]);
            System.out.println(input[i]);
        }
    }

}
