package com.leetcode.hard;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Game24
 * <p>
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 * <p>
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 * <p>
 * <p>
 * https://leetcode.com/problems/24-game/
 */
public class Game24 extends Solution {

    public boolean judgePoint24(int[] nums) {
        List<Fraction> tmps = new ArrayList<>();
        for (int num : nums)
            tmps.add(new Fraction(num));
        return doJudge(tmps);
    }

    public boolean doJudge(List<Fraction> nums) {
        if (nums.size() == 2)
            return doJudge2(nums.get(0), nums.get(1));
        for (int i = 0; i <= nums.size() - 2; i++) {
            Fraction a = nums.get(i);
            List<Fraction> tmps_i = new ArrayList<>(nums);
            tmps_i.remove(i);
            for (int j = i; j <= tmps_i.size() - 1; j++) {
                Fraction b = tmps_i.get(j);
                List<Fraction> tmps = new ArrayList<>(tmps_i);
                tmps.remove(j);
                tmps.add(0, a.add(b));
                if (doJudge(tmps))
                    return true;
                tmps.remove(0);
                tmps.add(0, a.subtract(b));
                if (doJudge(tmps))
                    return true;
                tmps.remove(0);
                tmps.add(0, a.multiply(b));
                if (doJudge(tmps))
                    return true;
                tmps.remove(0);
                tmps.add(0, a.divide(b));
                if (doJudge(tmps))
                    return true;
                tmps.remove(0);
                tmps.add(0, b.subtract(a));
                if (doJudge(tmps))
                    return true;
                tmps.remove(0);
                tmps.add(0, b.divide(a));
                if (doJudge(tmps))
                    return true;
            }
        }
        return false;
    }

    public boolean doJudge2(Fraction a, Fraction b) {
        if (a.add(b).is24())
            return true;
        if (a.subtract(b).is24() || b.subtract(a).is24())
            return true;
        if (a.multiply(b).is24())
            return true;
        if (a.divide(b).is24() || b.divide(a).is24())
            return true;
        return false;
    }

    public class Fraction {

        int molecule;

        int denominator;

        public Fraction(int molecule, int denominator) {
            this.molecule = molecule;
            this.denominator = denominator;
        }

        public Fraction(int molecule) {
            this(molecule, 1);
        }

        public Fraction add(Fraction f) {
            return new Fraction(this.molecule * f.denominator + this.denominator * f.molecule, this.denominator * f.denominator);
        }

        public Fraction subtract(Fraction f) {
            return new Fraction(this.molecule * f.denominator - this.denominator * f.molecule, this.denominator * f.denominator);
        }

        public Fraction multiply(Fraction f) {
            return new Fraction(this.molecule * f.molecule, this.denominator * f.denominator);
        }

        public Fraction divide(Fraction f) {
            return new Fraction(this.molecule * f.denominator, this.denominator * f.molecule);
        }

        public boolean is24() {
            try {
                return molecule % denominator == 0 && molecule / denominator == 24;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public void test() {
        System.out.println(judgePoint24(new int[]{1, 8, 2, 5}));
    }
}
