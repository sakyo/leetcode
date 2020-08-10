package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.*;

/**
 * Guess The Word
 * <p>
 * This problem is an interactive problem new to the LeetCode platform.
 * <p>
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 * <p>
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 * <p>
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 * <p>
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 * <p>
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 * <p>
 * Example 1:
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 * <p>
 * Explanation:
 * <p>
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * <p>
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * https://leetcode.com/problems/guess-the-word/
 */
public class GuessTheWord extends Solution {

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> source = new ArrayList<String>(Arrays.asList(wordlist));
        List<Map<Integer, Set<Integer>>> cache = build(source);
        int min = wordlist.length;
        int index = 0;
        for (int i = 0; i < source.size(); i++) {
            int tmp;
            if ((tmp = score(cache.get(i), null)) < min) {
                min = tmp;
                index = i;
            }
        }
        int match = 0;
        Set<Integer> matched = new HashSet<>();
        while ((match = master.guess(source.get(index))) != 6) {
            matched.add(index);
            Set<Integer> tolist = cache.get(index).get(match);
            min = wordlist.length;
            index = 0;
            for (Integer i : tolist) {
                if (matched.contains(i))
                    continue;
                int tmp;
                if ((tmp = score(cache.get(i), matched)) < min) {
                    min = tmp;
                    index = i;
                }
            }
        }
        return;
    }

    public int score(Map<Integer, Set<Integer>> map, Set<Integer> matched) {
        int score = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            int max = 0;
            for (Integer i : entry.getValue()) {
                if (matched == null || !matched.contains(i))
                    max += 1;
            }
            if (max > score)
                score = max;
        }
        return score;
    }

    public List<Map<Integer, Set<Integer>>> build(List<String> source) {
        List<Map<Integer, Set<Integer>>> result = new ArrayList<>();
        for (String s : source) {
            Map<Integer, Set<Integer>> cache = new HashMap<>();
            for (int i = 0; i < source.size(); i++) {
                int c = compare(s, source.get(i));
                if (!cache.containsKey(c))
                    cache.put(c, new HashSet<>());
                cache.get(c).add(i);
            }
            result.add(cache);
        }
        return result;
    }

    public int compare(String a, String b) {
        int i = 0;
        for (int j = 0; j < 6; j++)
            if (a.charAt(j) == b.charAt(j))
                i++;
        return i;
    }

    @Override
    public void test() {
        findSecretWord(new String[]{"gaxckt", "trlccr", "jxwhkz", "ycbfps", "peayuf", "yiejjw", "ldzccp", "nqsjoa", "qrjasy", "pcldos", "acrtag", "buyeia", "ubmtpj", "drtclz", "zqderp", "snywek", "caoztp", "ibpghw", "evtkhl", "bhpfla", "ymqhxk", "qkvipb", "tvmued", "rvbass", "axeasm", "qolsjg", "roswcb", "vdjgxx", "bugbyv", "zipjpc", "tamszl", "osdifo", "dvxlxm", "iwmyfb", "wmnwhe", "hslnop", "nkrfwn", "puvgve", "rqsqpq", "jwoswl", "tittgf", "evqsqe", "aishiv", "pmwovj", "sorbte", "hbaczn", "coifed", "hrctvp", "vkytbw", "dizcxz", "arabol", "uywurk", "ppywdo", "resfls", "tmoliy", "etriev", "oanvlx", "wcsnzy", "loufkw", "onnwcy", "novblw", "mtxgwe", "rgrdbt", "ckolob", "kxnflb", "phonmg", "egcdab", "cykndr", "lkzobv", "ifwmwp", "jqmbib", "mypnvf", "lnrgnj", "clijwa", "kiioqr", "syzebr", "rqsmhg", "sczjmz", "hsdjfp", "mjcgvm", "ajotcx", "olgnfv", "mjyjxj", "wzgbmg", "lpcnbj", "yjjlwn", "blrogv", "bdplzs", "oxblph", "twejel", "rupapy", "euwrrz", "apiqzu", "ydcroj", "ldvzgq", "zailgu", "xgqpsr", "wxdyho", "alrplq", "brklfk"}, new Master() {
            @Override
            public int guess(String word) {
                return compare(word, "hbaczn");
            }
        });
    }

    interface Master {

        int guess(String word);
    }

}
