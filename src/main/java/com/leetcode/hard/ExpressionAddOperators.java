package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p/>
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * Credits:
 * Special thanks to @davidtan1890 for adding this problem and creating all test cases.
 * <p/>
 * Subscribe to see which companies asked this question
 * <p/>
 * Show Tags
 * Show Similar Problems
 * <p/>
 * https://leetcode.com/problems/expression-add-operators/
 */
public class ExpressionAddOperators {

    public static class Solution {

        private Set<String> results = new HashSet<String>();

        public List<String> addOperators(String num, int target) {
            results.clear();
            if (num != null && num.length() > 0) {
                char[] nums = num.toCharArray();
                char[] result = new char[nums.length * 2 - 1];
                result[0] = nums[0];
                add(target, 1, nums, result, 0, true, nums[0]-48, 0);
            }
            return new ArrayList<String>(results);
        }

        // + - * , 0,1,2
        // N1 op Group ...
        public void add(int target, int pos, char[] nums, char[] result, long n1, boolean op, long group, long lastnum) {
            // 计算结果
            if (pos >= nums.length) {
                if (op && n1 + group == target)
                    toString(result);
                else if (!op && n1 - group == target)
                    toString(result);
                return;
            }
            // 塞入数据
            result[pos * 2] = nums[pos];
            int value = nums[pos] - 48;
            // 加号,减,乘 分别走一遍
            result[pos * 2 - 1] = '+';
            add(target, pos + 1, nums, result, op ? n1 + group : n1 - group, true, value, 0);
            result[pos * 2 - 1] = '-';
            add(target, pos + 1, nums, result, op ? n1 + group : n1 - group, false, value, 0);
            result[pos * 2 - 1] = '*';
            add(target, pos + 1, nums, result, n1, op, value * group, 0);
            if(lastnum != 0 || nums[pos-1] !=48) {
                result[pos * 2 - 1] = ' ';
                if(lastnum == 0)
                    lastnum = nums[pos-1]-48;
                add(target, pos + 1, nums, result, n1, op,  group/lastnum*(lastnum*10+value), lastnum*10+value);
            }
        }

        public void toString(char[] result) {
            char[] ss = new char[result.length];
            int i = 0 ;
            for(int j =0 ;j<result.length;j++)
                if(result[j]!=' ') {
                    ss[i] = result[j];
                    i++;
                }
            results.add(new String(ss,0,i));
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        System.out.println(new Solution().addOperators("2147483648", -2147483648).size());
        System.out.println(new Solution().addOperators("2147483647", -2147483647).size());
        System.out.println(new Solution().addOperators("105", 5).size());
//        System.out.println(new Solution().addOperators("62374", 9176).size());
//        System.out.println(new Solution().addOperators("3456237490", 9192).size());
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }
}
