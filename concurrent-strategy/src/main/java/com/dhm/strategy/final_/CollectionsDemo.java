package com.dhm.strategy.final_;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/4 10:14
 */
@Slf4j
public class CollectionsDemo {

    private static Map<Integer,Integer> map;
    private static Set<Integer> set;
    private static List<Integer> list;

    static{
        map = Collections.unmodifiableMap(Maps.newHashMap());
        set = Collections.unmodifiableSet(Sets.newHashSet());
        list = Collections.unmodifiableList(Lists.newArrayList());
    }

    public static void main(String[] args) {

        map.put(1,2);
        log.info("map:{}",map.get(1));

        set.add(1);
        log.info("set:{}",set.iterator().next());

        list.add(1);
        log.info("list:{}",list.get(0));
    }
}