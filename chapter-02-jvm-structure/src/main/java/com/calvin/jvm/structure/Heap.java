package com.calvin.jvm.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 堆
 *
 * @author Calvin
 * @date 2023/4/26
 * @since v1.0.0
 */
public class Heap {

    /**
     * 主方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        String s1 = new String("你好！我是堆S1");
        String s2 = new String("你好！我是堆S2");
        List<String> l3 = Stream.of("你好！我是堆L3").collect(Collectors.toList());
    }
}
