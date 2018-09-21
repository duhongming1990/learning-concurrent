package com.dhm.strategy.final_;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/4 9:59
 */
@Slf4j
public class FinalDemo {

    private static final int i = 1;
    private static final String s = "";
    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        i = 2;
//        s = "s";
        map.put(1, 100);
        System.out.println("map = " + map.get(1));
    }
}