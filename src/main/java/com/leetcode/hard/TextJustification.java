package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * <p/>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * <p/>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p/>
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * <p/>
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * <p/>
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * <p/>
 * https://leetcode.com/problems/text-justification/
 */
public class TextJustification {

//    public static class Solution {
//
//        private char[] spaces;
//
//        private String[] words;
//
//        public List<String> fullJustify(String[] words, int maxWidth) {
//            char[] spaces = new char[maxWidth];
//            for (int i = 0; i < spaces.length; i++)
//                spaces[i] = ' ';
//            List<String> result = new ArrayList<String>();
//            int index = 0;
//            int picked_length = 0; // 本行已经选择的词的长度
//            int picked_words = 0;  // 本行已经选择的词的个数
//            while (i < words.length) {
//                if ((picked_length + words[i].length() + picked_words) > maxWidth) {
//                    char[] chars = new char[maxWidth];
//                    if (picked_words == 1) {
//                        System.arraycopy(words[i - 1].toCharArray(), 0, chars, 0, words[i - 1].length());
//                        System.arraycopy(spaces, 0, chars, picked_length, maxWidth - picked_length);
//                    } else {
//                    }
//                    result.add(new String(chars));
//                }
//            }
//        }
//
//        private String buildStr(int picked_words, int picked_length, int index) {
//            char[] chars = new char[spaces.length];
//            if (index >= words.length-1) {
//                int j = 0;
//                for (int i = 0; i < picked_words; i++) {
//                    System.arraycopy(words[i - picked_words + 1].toCharArray(), 0, chars, j, words[i - picked_words + 1].length());
//                    j += words[i - picked_words + 1].length();
//                    if (i != picked_words - 1) {
//                        chars[j] = spaces[0];
//                        j++;
//                    }else if(j<chars.length-1){
//                        System.arraycopy(spaces, 0, chars, j+1, chars.length-j-1);
//                    }
//                }
//                return new String(chars);
//            }else{
//
//            }
//        }
//    }
}
