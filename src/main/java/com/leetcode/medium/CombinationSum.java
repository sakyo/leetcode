package com.leetcode.medium;

import com.leetcode.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * CombinationSum
 * <p>
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum extends Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, target, 0);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, int index) {
        List<List<Integer>> result = new ArrayList<>();
        if (index >= candidates.length)
            return result;
        if (candidates[index] < target) {
            for (List<Integer> list : combinationSum(candidates, target - candidates[index], index)) {
                list.add(candidates[index]);
                result.add(list);
            }
        }
        if (candidates[index] == target) {
            List<Integer> list = new ArrayList<>();
            list.add(target);
            result.add(list);
        }
        result.addAll(combinationSum(candidates, target, index + 1));
        return result;
    }

    @Override
    public void test() {
    }
}
