package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * StrongPasswordChecker
 * <p>
 * A password is considered strong if below conditions are all met:
 * <p>
 * It has at least 6 characters and at most 20 characters.
 * It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
 * Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.
 * <p>
 * Insertion, deletion or replace of any one character are all considered as one change.
 * <p>
 * https://leetcode.com/problems/strong-password-checker/
 */
public class StrongPasswordChecker extends Solution {

    public int strongPasswordChecker(String s) {
        boolean has_low = false;
        boolean has_uppercase = false;
        boolean has_digit = false;
        int rep_count = 1;
        List<Integer> cache = new ArrayList<>();
        // 分析
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                has_low = true;
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                has_uppercase = true;
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                has_digit = true;
            if (i > 0) {
                if (s.charAt(i) == s.charAt(i - 1))
                    rep_count++;
                else {
                    if (rep_count > 2)
                        cache.add(rep_count);
                    rep_count = 1;
                }
            }
        }
        // 补最后一位
        if (rep_count > 2)
            cache.add(rep_count);
        // 待特殊
        int need = (has_low ? 0 : 1) + (has_uppercase ? 0 : 1) + (has_digit ? 0 : 1);
        // 待增加
        int to_add = s.length() < 6 ? 6 - s.length() : 0;
        // 待删除
        int to_del = s.length() > 20 ? s.length() - 20 : 0;
        int result = 0;
        if (to_add > 0) {
            for (Integer c : cache) {
                int todo = add(c);
                to_add -= todo;
                need -= todo;
                result += todo;
            }
        } else {
            // 先删满3的
            for (int i = 0; i < cache.size(); i++) {
                if (to_del < 1)
                    break;
                if (cache.get(i) % 3 == 0) {
                    result += 1;
                    cache.set(i, cache.get(i) - 1);
                    to_del -= 1;
                }
            }
            // 再删除多1的
            for (int i = 0; i < cache.size(); i++) {
                if (to_del < 2)
                    break;
                if (cache.get(i) % 3 == 1) {
                    result += 2;
                    cache.set(i, cache.get(i) - 2);
                    to_del -= 2;
                }
            }
            // 再删除多2的
            for (int i = 0; i < cache.size(); i++) {
                if (to_del < 3)
                    break;
                int d = cache.get(i) - 2;
                if (d > to_del)
                    d = to_del - (to_del % 3);
                result += d;
                cache.set(i, cache.get(i) - d);
                to_del -= d;
            }
            // 再replace
            for (int i = 0; i < cache.size(); i++) {
                int todo = replace(cache.get(i));
                need -= todo;
                result += todo;
            }
        }
        int last = Math.max(need, to_add);
        if (last > 0)
            result += last;
        if (to_del > 0)
            result += to_del;
        return result;
    }

    private int replace(int n) {
        return n / 3;
    }

    private int add(int n) {
        return (n + 1) / 2 - 1;
    }

    private int delete(int n) {
        return n - 2 > 0 ? n - 2 : 0;
    }

    @Override
    public void test() {
        System.out.println(strongPasswordChecker("AAAAAABBBBBB123456789a"));
    }
}
