package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a chemical formula (given as a string), return the count of each atom.
 * <p>
 * An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 * <p>
 * 1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
 * <p>
 * Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
 * <p>
 * A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
 * <p>
 * Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 * <p>
 * https://leetcode.com/problems/number-of-atoms/
 */
public class NumberOfAtoms {

    int pos;

    char[] chars;

    public String countOfAtoms(String formula) {
        chars = formula.toCharArray();
        pos = 0;
        Map<Integer, Integer> result = visitNoBrackets(new TreeMap<Integer, Integer>());
        String output = new String();
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            char first = (char) (entry.getKey() / 27 + 'A');
            String key;
            if (entry.getKey() % 27 != 0) {
                char second = (char) (entry.getKey() % 27 + 'a' - 1);
                key = "" + first + second;
            } else
                key = first + "";
            if (entry.getValue() > 1)
                output = output + key + entry.getValue();
            else
                output = output + key;
        }
        return output;
    }

    public Map<Integer, Integer> visitNoBrackets(TreeMap<Integer, Integer> nodes) {
        if (pos > chars.length - 1 || chars[pos] == ')') {
            pos++;
            return nodes;
        }
        if (chars[pos] == '(') {
            pos++;
            Map<Integer, Integer> newnodes = visitNoBrackets(new TreeMap<Integer, Integer>());
            int i = visitcount();
            if (i > 1)
                for (Map.Entry<Integer, Integer> entry : newnodes.entrySet())
                    entry.setValue(entry.getValue() * i);
            return visitNoBrackets((TreeMap<Integer, Integer>) merge(nodes, newnodes));
        }
        int key = visitkey();
        int value = visitcount();
        Integer oldvalue = nodes.get(key);
        if (oldvalue != null)
            nodes.put(key, oldvalue + value);
        else
            nodes.put(key, value);
        return visitNoBrackets(nodes);
    }

    public int visitcount() {
        int count = 1;
        if (pos <= chars.length - 1 && chars[pos] <= '9' && chars[pos] >= '0') {
            count = chars[pos] - '0';
            pos++;
        }
        if (pos <= chars.length - 1 && chars[pos] <= '9' && chars[pos] >= '0') {
            count = (chars[pos] - '0')+10*count;
            pos++;
        }
        return count;
    }

    public int visitkey() {
        int i = chars[pos] - 'A';
        pos++;
        int j = 0;
        if (pos <= chars.length - 1 && chars[pos] >= 'a') {
            j = chars[pos] - 'a' + 1;
            pos++;
        }
        return i * 27 + j;
    }

    public Map<Integer, Integer> merge(Map<Integer, Integer> first, Map<Integer, Integer> last) {
        for (Map.Entry<Integer, Integer> entry : last.entrySet()) {
            Integer oldvaluel = first.get(entry.getKey());
            if (oldvaluel == null)
                first.put(entry.getKey(), entry.getValue());
            else
                first.put(entry.getKey(), oldvaluel + entry.getValue());
        }
        return first;
    }

    public static void main(String[] args) {
        //nodes.add(build(input4, 0));
        //new int{,,};
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        //System.out.println(new NumberOfAtoms().countOfAtoms("K4(ON(SO3)2)2"));//
        System.out.println(new NumberOfAtoms().countOfAtoms("B(((Ag)3)C2)5"));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
