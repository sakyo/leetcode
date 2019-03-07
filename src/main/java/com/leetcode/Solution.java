package com.leetcode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Solution Base
 *
 * @author yangzhongwei
 * @date 2015-08-27 08:05
 */
public abstract class Solution {
    public abstract void test();

    public static void main(String[] args) throws Exception {
        String classname = System.getProperty("sun.java.command").split(" ")[0];
        Solution solution = (Solution) Class.forName(classname).newInstance();
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
        solution.test();
        System.out.println(new SimpleDateFormat("hh:mm:ss SSS").format(new Date()));
    }

}
