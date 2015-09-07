package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p/>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 * <p/>
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p/>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * <p/>
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder implements Solution {

    /**
     * 594ms
     *
     * @param wordDict
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        List<String> queue = new ArrayList<String>();
        Set<String> visited = new HashSet<String>();
        wordDict.add(endWord);
        visited.add(beginWord);
        queue.add(beginWord);
        queue.add(null);
        int level = 1;
        boolean founed = false;
        while (queue.size() > 1) {
            String head = queue.get(0);
            queue.remove(0);
            if (head == null) {
                queue.add(null);
                level++;
                continue;
            }
            if(endWord.equals(head)){
                founed = true;
                break;
            }
            char[] heads = head.toCharArray();
            for(int i = 0; i<heads.length;i++){
                char or = heads[i];
                for(char ch = 'a';ch<='z';ch++){
                    heads[i] = ch;
                    String tmp = new String(heads);
                    if(wordDict.contains(tmp) && !visited.contains(tmp)){
                        visited.add(tmp);
                        queue.add(tmp);
                    }
                }
                heads[i] = or;
            }
        }
        if (founed)
            return level;
        else
            return 0;
    }
}
