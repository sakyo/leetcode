package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * WordSquares
 * <p>
 * 给定一个单词集合 （没有重复），找出其中所有的 单词方块 。
 * <p>
 * 一个单词序列形成了一个有效的单词方块的意思是指从第 k 行和第 k 列 (0 ≤ k < max(行数, 列数)) 来看都是相同的字符串。
 * <p>
 * 例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
 * <p>
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * 注意：
 * <p>
 * 单词个数大于等于 1 且不超过 500。
 * 所有的单词长度都相同。
 * 单词长度大于等于 1 且不超过 5。
 * 每个单词只包含小写英文字母 a-z。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["area","lead","wall","lady","ball"]
 * <p>
 * 输出：
 * [
 * [ "wall",
 * "area",
 * "lead",
 * "lady"
 * ],
 * [ "ball",
 * "area",
 * "lead",
 * "lady"
 * ]
 * ]
 * <p>
 * 解释：
 * 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
 *  
 * <p>
 * 示例 2：
 * <p>
 * 输入：
 * ["abat","baba","atan","atal"]
 * <p>
 * 输出：
 * [
 * [ "baba",
 * "abat",
 * "baba",
 * "atan"
 * ],
 * [ "baba",
 * "abat",
 * "baba",
 * "atal"
 * ]
 * ]
 * <p>
 * 解释：
 * 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
 *  
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-squares
 *
 * @date 2020-04-12 19:10
 */
public class WordSquares extends Solution {

    class Node {

        Node[] nodes = new Node[26];

        List<Integer> words = new ArrayList<>();
    }

    public List<List<String>> wordSquares(String[] words) {
        Node root = new Node();
        int i = 0;
        for (String word : words) {
            Node s = root;
            s.words.add(i);
            for (char c : word.toCharArray()) {
                if (s.nodes[c - 'a'] == null)
                    s.nodes[c - 'a'] = new Node();
                s.nodes[c - 'a'].words.add(i);
                s = s.nodes[c - 'a'];
            }
            i++;
        }
        return find(new ArrayList<>(), root, words);
    }

    public List<List<String>> find(List<String> start, Node root, String[] words) {
        List<List<String>> result = new ArrayList<>();
        if (start.size() == words[0].length()) {
            result.add(new ArrayList<>(start));
            return result;
        }
        Node new_root = root;
        for (String s : start) {
            new_root = new_root.nodes[s.charAt(start.size()) - 'a'];
            if (new_root == null)
                return result;
        }
        for (Integer i : new_root.words) {
            start.add(words[i]);
            List<List<String>> finded = find(start, root, words);
            start.remove(start.size() - 1);
            result.addAll(finded);
        }
        return result;
    }

    @Override
    public void test() {
        System.out.println(wordSquares(new String[]{"abat", "baba", "atan", "atal"}));
    }
}
