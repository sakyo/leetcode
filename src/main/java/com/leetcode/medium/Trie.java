package com.leetcode.medium;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p/>
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * <p/>
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class Trie {

    class TrieNode {

        boolean isWord = false;

        TrieNode[] children = new TrieNode[26];

        // Initialize your data structure here.
        public TrieNode() {
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null)
            return;
        char[] words = word.toCharArray();
        TrieNode start = root;
        for (char c : words) {
            int i = c - 'a';
            if (start.children[i] == null) {
                start.children[i] = new TrieNode();
            }
            start = start.children[i];
        }
        start.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(word == null)
            return false;
        char[] words = word.toCharArray();
        TrieNode start = root;
        for (char c : words) {
            int i = c - 'a';
            if (start.children[i] == null) {
                return false;
            }
            start = start.children[i];
        }
        return start.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(prefix == null)
            return false;
        char[] words = prefix.toCharArray();
        TrieNode start = root;
        for (char c : words) {
            int i = c - 'a';
            if (start.children[i] == null) {
                return false;
            }
            start = start.children[i];
        }
        return !start.isWord;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("somestring");
        trie.search("key");
    }
}