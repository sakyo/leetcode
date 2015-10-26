package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Validate if a given string is numeric.
 * <p/>
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 * <p/>
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
 * <p/>
 * https://leetcode.com/problems/valid-number/
 */
public class ValidNumber {

    public static class Solution {

        public static final char NUM_START = '0';

        public static final char NUM_END = '9';

        public static final char A_START = 'A';

        public static final char F_END = 'F';

        public static final char a_START = 'a';

        public static final char f_END = 'f';

        public static final char E = 'E';

        public static final char e = 'e';

        public static final char POINT = '.';

        public boolean isNumber(String s) {
            if (s == null || (s = s.trim()).length() == 0)
                return false;
            int pos = 0;
            for (char c : s.toCharArray()) {
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
        System.out.println(new Solution().isNumber("1212121212121212"));
        System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
