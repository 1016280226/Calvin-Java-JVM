package com.calvin.jvm.structure.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存泄漏-示例
 *
 * @author Calvin
 * @date 2023/6/28
 * @since v1.0.0
 */
public class HeapMemoryLeakExample {

    public static void invokeUserNoGcClean() {
        List list = new ArrayList<>(10);
        for (int i = 0; i < 100 ; i++) {
            HeapMemoryOutOfExample.User user = new HeapMemoryOutOfExample.User();
            list.add(user);
            user = null;
        }

        try {
            System.gc();
            Thread.sleep(50000000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
            invokeUserNoGcClean();
    }
}
