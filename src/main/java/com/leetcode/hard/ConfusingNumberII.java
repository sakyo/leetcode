package com.leetcode.hard;

import com.leetcode.Solution;

/**
 * ConfusingNumberII
 * <p>
 * 本题我们会将数字旋转 180° 来生成一个新的数字。
 * <p>
 * 比如 0、1、6、8、9 旋转 180° 以后，我们得到的新数字分别为 0、1、9、8、6。
 * <p>
 * 2、3、4、5、7 旋转 180° 后，是 无法 得到任何数字的。
 * <p>
 * 易混淆数（Confusing Number）指的是一个数字在整体旋转 180° 以后，能够得到一个和原来 不同 的数，且新数字的每一位都应该是有效的。（请注意，旋转后得到的新数字可能大于原数字）
 * <p>
 * 给出正整数 N，请你返回 1 到 N 之间易混淆数字的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：20
 * 输出：6
 * 解释：
 * 易混淆数为 [6,9,10,16,18,19]。
 * 6 转换为 9
 * 9 转换为 6
 * 10 转换为 01 也就是 1
 * 16 转换为 91
 * 18 转换为 81
 * 19 转换为 61
 * 示例 2：
 * <p>
 * 输入：100
 * 输出：19
 * 解释：Reentrant
 * 易混淆数为 [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10^9
 * <p>
 * https://leetcode-cn.com/problems/confusing-number-ii
 *
 * @author yangzhongwei
 * @date 2020-03-05 12:10
 */
public class ConfusingNumberII extends Solution {

    /**
     * 分为三个部分，
     * 1）小于当前位数，我们当做0开始的，递归算
     * 2）小于当前开始值的，直接乘积减去可能的
     * 3）等于当前开始的，另外算
     */
    public int confusingNumberII(int N) {
        char[] chars = (N + "").toCharArray();
        int result = get_1(chars.length - 1);
        boolean is_symmetry = true;
        boolean is_last = true;
        for (int i = 0; i < chars.length; i++) {
            int p = check(chars[i] - '0');
            if (i == 0 && chars.length > 1)
                p--;
            if (i < chars.length / 2) {
                int m = chars.length % 2 == 1 ? 3 : 1;
                result += p * (Math.pow(5, chars.length - 1 - i) - m * Math.pow(5, chars.length / 2 - 1 - i));
            } else if (i == chars.length / 2 && chars.length % 2 == 1) {
                // 算数量
                int q = 0;
                if (chars[i] > '0')
                    q = 1;
                if (chars[i] > '1')
                    q = 2;
                if (chars[i] > '8')
                    q = 3;
                result += p * Math.pow(5, chars.length - 1 - i) - q * Math.pow(5, chars.length / 2 - 1 - i > 0 ? chars.length / 2 - 1 - i : 0);
                // 判断当前是否对称
                is_symmetry = (chars[i] == '0' || chars[i] == '1' || chars[i] == '8');
            } else {
                int q = is_symmetry && chars[i] > get_2(chars[chars.length - 1 - i]) ? 1 : 0;
                result += p * Math.pow(5, chars.length - 1 - i) - q;
                if (is_symmetry && chars[i] == get_2(chars[chars.length - 1 - i]))
                    is_symmetry = true;
                else
                    is_symmetry = false;
            }
            if (!isNum(chars[i] - '0')) {
                is_last = false;
                break;
            }
        }
        if (is_last && !is_symmetry)
            result++;
        return result;
    }

    public int get_1(int level) {
        if (level == 0)
            return 0;
        if (level == 1)
            return 2;
        int result = 1;
        if (level % 2 == 1)
            result = 3;
        return new Double(4 * (Math.pow(5, level - 1) - result * Math.pow(5, level / 2 - 1))).intValue() + get_1(level - 1);
    }

    public char get_2(char p) {
        switch (p) {
            case '0':
            case '1':
            case '8':
                return p;
            case '6':
                return '9';
            case '9':
                return '6';
        }
        return p;
    }

    public boolean isNum(int p) {
        return p == 0 || p == 1 || p == 6 || p == 8 || p == 9;
    }

    /**
     * 小于p的可变数字有几个
     */
    public int check(int p) {
        switch (p) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 2;
            case 4:
                return 2;
            case 5:
                return 2;
            case 6:
                return 2;
            case 7:
                return 3;
            case 8:
                return 3;
            case 9:
                return 4;
        }
        return 0;
    }

    @Override
    public void test() {
        System.out.println(confusingNumberII(234389749));
        System.out.println(confusingNumberII(20));
        System.out.println(confusingNumberII(100));
        System.out.println(confusingNumberII(107));
    }
}
