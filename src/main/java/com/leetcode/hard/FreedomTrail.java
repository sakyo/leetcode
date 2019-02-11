package com.leetcode.hard;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.
 * <p>
 * Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.
 * <p>
 * Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.
 * <p>
 * At the stage of rotating the ring to spell the key character key[i]:
 * <p>
 * You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
 * If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
 * Example:
 * <p>
 * <p>
 * <p>
 * Input: ring = "godding", key = "gd"
 * Output: 4
 * Explanation:
 * For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 * For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 * Also, we need 1 more step for spelling.
 * So the final output is 4.
 * Note:
 * <p>
 * Length of both ring and key will be in range 1 to 100.
 * There are only lowercase letters in both strings and might be some duplcate characters in both strings.
 * It's guaranteed that string key could always be spelled by rotating the string ring.
 * <p>
 * https://leetcode.com/problems/freedom-trail/
 */
public class FreedomTrail {

    private int[][] cache;

    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> rings = new HashMap<Character, List<Integer>>();
        for(int i = 0; i< ring.length(); i++) {
            if (!rings.containsKey(ring.charAt(i)))
                rings.put(ring.charAt(i), new ArrayList<Integer>());
            rings.get(ring.charAt(i)).add(i);
        }
        cache = new int[ring.length()][key.length()];
        return findRotateSteps(rings, key, 0, 0, ring.length());
    }

    public int findRotateSteps(Map<Character, List<Integer>> rings, String key, int current_r, int current_k, int length) {
        if(current_k >= key.length())
            return 0;
        if(cache[current_r][current_k] != 0)
            return cache[current_r][current_k];
        List<Integer> poses = rings.get(key.charAt(current_k));
        // 减枝优化
        if(poses.contains(current_r))
            return cache[current_r][current_k] = (1 + findRotateSteps(rings, key, current_r, current_k+1 ,length));
        int min = Integer.MAX_VALUE;
        for(Integer pos: poses){
            int count = pos + length - current_r;
            count = count % length;
            if(count > length/2)
                count = length - count;
            int cost = count + 1 + findRotateSteps(rings, key, pos, current_k+1, length);
            if(cost < min)
                min = cost;
        }
        return cache[current_r][current_k] = min;
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        System.out.println(new FreedomTrail().findRotateSteps("caotmcaataijjxi","oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"));
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }
}
