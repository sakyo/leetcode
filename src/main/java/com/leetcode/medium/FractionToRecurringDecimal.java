package com.leetcode.medium;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * FractionToRecurringDecimal
 * <p>
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * <p>
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 */
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if((numerator>0 && denominator<0)||(numerator<0 && denominator>0))
            sb.append('-');
        long den = Math.abs(denominator+0L);
        long bigger = Math.abs(numerator+0L) / den;
        long reset = Math.abs(numerator+0L) % den;
        sb.append(bigger);
        if (reset == 0)
            return sb.toString();
        sb.append('.');
        LinkedHashMap<Long, Integer> cache = new LinkedHashMap<Long, Integer>();
        int i = sb.length();
        while (reset != 0) {
            reset = reset * 10;
            int j = 0;
            while (reset < den) {
                sb.append('0');
                i++;
                j++;
                reset = reset * 10;
            }
            // 之前是否除过
            Integer pos = cache.get(reset);
            if(pos != null) {
                if(j!= 0)
                    sb.delete(sb.length()-j, sb.length());
                sb.insert(pos-j, "(");
                sb.append(')');
                break;
            }
            cache.put(reset, i);
            sb.append(reset / den);
            reset = reset % den;
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        System.out.println(new FractionToRecurringDecimal().fractionToDecimal(-1,-2147483648));
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }
}
