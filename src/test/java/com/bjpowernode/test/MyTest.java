package com.bjpowernode.test;

import com.bjpowernode.utils.MD5Util;
import org.junit.Test;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:07
 * 描述：
 */
public class MyTest {
    @Test
    public void testMd5(){
        String mi = MD5Util.getMD5("000000");
        System.out.println(mi);
    }
}
