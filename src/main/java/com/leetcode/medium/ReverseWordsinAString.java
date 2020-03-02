package com.leetcode.medium;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ReverseWordsinAString
 * <p>
 * Given an input string, reverse the string word by word.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 * <p>
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 * <p>
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsinAString extends Solution {

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c == ' '){
                if(tmp.length() >0){
                    list.add(tmp.toString());
                    tmp = new StringBuilder();
                }
            }else {
                tmp.append(c);
            }
        }
        if(tmp.length() >0){
            list.add(tmp.toString());
        }
        tmp = new StringBuilder();
        Collections.reverse(list);
        for (String ss : list) {
            tmp.append(ss).append(' ');
        }
        if(tmp.length() >0)
            tmp.deleteCharAt(tmp.length()-1);
        return tmp.toString();
    }

    @Override
    public void test() {
        System.out.println(reverseWords("  world!  "));
    }
}
