package com.dhm.strategy.final_;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/4 10:21
 */

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ImmutableDemo {

    private static ImmutableMap<Integer,Integer> immutableMap1 = ImmutableMap.of(1,2,3,4);
    private static ImmutableMap<Integer,Integer> immutableMap2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2)
            .put(3,4)
            .build();

    private static ImmutableSet<Integer> immutableSet1 = ImmutableSet.of(1,2);
    private static ImmutableSet<Integer> immutableSet2 = ImmutableSet.<Integer>builder()
            .add(1)
            .add(2)
            .build();

    private static ImmutableList<Integer> immutableList1 = ImmutableList.of(1,2);
    private static ImmutableList<Integer> immutableList2 = ImmutableList.<Integer>builder()
            .add(1)
            .add(2)
            .build();

    public static void main(String[] args) {

        immutableMap1.put(1,100);
        immutableMap2.put(1,100);

        immutableSet1.add(3);
        immutableSet2.add(3);

        immutableList1.add(3);
        immutableList2.add(3);
    }
}