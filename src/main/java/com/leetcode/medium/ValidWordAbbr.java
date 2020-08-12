package com.leetcode.medium;

import com.leetcode.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Unique-Word-Abbreviation
 * <p>
 * 一个单词的缩写需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。
 * <p>
 * 以下是一些单词缩写的范例：
 * <p>
 * a) it                      --> it    (没有缩写)
 * <p>
 * 1
 * ↓
 * b) d|o|g                   --> d1g
 * <p>
 * 1    1  1
 * 1---5----0----5--8
 * ↓   ↓    ↓    ↓  ↓
 * c) i|nternationalizatio|n  --> i18n
 * <p>
 * 1
 * 1---5----0
 *      ↓   ↓    ↓
 * d) l|ocalizatio|n          --> l10n
 * 请你判断单词缩写在字典中是否唯一。当单词的缩写满足下面任何一个条件是，可以认为该单词缩写是唯一的：
 * <p>
 * 字典 dictionary 中没有任何其他单词的缩写与该单词 word 的缩写相同。
 * 字典 dictionary 中的所有缩写与该单词 word 的缩写相同的单词都与 word 相同。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["ValidWordAbbr","isUnique","isUnique","isUnique","isUnique"]
 * [[["deer","door","cake","card"]],["dear"],["cart"],["cane"],["make"]]
 * 输出：
 * [null,false,true,false,true]
 * <p>
 * 解释：
 * ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
 * validWordAbbr.isUnique("dear"); // return False
 * validWordAbbr.isUnique("cart"); // return True
 * validWordAbbr.isUnique("cane"); // return False
 * validWordAbbr.isUnique("make"); // return True
 *  
 * <p>
 * 提示：
 * <p>
 * 每个单词都只由小写字符组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/unique-word-abbreviation
 *
 * @date 2020-08-12 20:55
 */
public class ValidWordAbbr extends Solution {

    private static final String FAIL = "FAIL";

    Map<String, String> cache = new HashMap<>();

    public ValidWordAbbr(){}

    public ValidWordAbbr(String[] dictionary) {
        for (String s : dictionary) {
            String abbr;
            if (s.length() < 3)
                abbr = s;
            else
                abbr = new StringBuilder().append(s.charAt(0)).append(s.length() - 2).append(s.charAt(s.length() - 1)).toString();
            if (!cache.containsKey(abbr)){
                cache.put(abbr, s);
            }else{
                String old = cache.get(abbr);
                if(!s.equals(old))
                    cache.put(abbr, FAIL);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr;
        if (word.length() < 3)
            abbr = word;
        else
            abbr = new StringBuilder().append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1)).toString();
        return !cache.containsKey(abbr) || cache.get(abbr).equals(word);
    }

    @Override
    public void test() {
        ValidWordAbbr obj = new ValidWordAbbr(new String[]{"deer","door","cake","card"});
        boolean param_1 = obj.isUnique("make");
    }
}
