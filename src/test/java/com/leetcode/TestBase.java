package com.leetcode;

import org.junit.After;
import org.junit.Before;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Record Time
 *
 * @author yangzhongwei
 * @date 2015-08-27 08:19
 */
public class TestBase {

    @Before
    @After
    public void recordTime() {
        System.out.println(new SimpleDateFormat("hh:mm:ss.SSS").format(new Date()));
    }
}
