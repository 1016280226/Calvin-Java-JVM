package com.calvin.jvm.structure.example;

import com.calvin.jvm.structure.Heap;
import jdk.jfr.internal.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出-例子
 *
 * @author Calvin
 * @date 2023/6/28
 * @since v1.0.0
 */
public class HeapMemoryOutOfExample {

    /**
     * 用户
     *
     * @author calvin
     * @date 2023/06/28
     */
    public static class User {
        /**
         * 1MB
         */
        private byte[] bytes = new byte[1024 * 1024];
    }


    /**
     * 案例一: 递归深度长 -> 导致内存溢出
     */
    public static void recursionDeepLong() {
        List list = new ArrayList<>(0);
        while (true) {
            list.add(new User());
        }

        /**
         * # 运行结果: java.lang.OutOfMemoryError: Java heap space (堆内存溢出)
         *
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * 	at java.util.Arrays.copyOf(Arrays.java:3210)
         * 	at java.util.Arrays.copyOf(Arrays.java:3181)
         * 	at java.util.ArrayList.grow(ArrayList.java:267)
         * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:241)
         * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:233)
         * 	at java.util.ArrayList.add(ArrayList.java:464)
         * 	at com.calvin.jvm.structure.example.HeapMemoryOutOfExample.main(HeapMemoryOutOfExample.java:35)
         */
    }


    /**
     * 案例二: 对象设置指定内存，超过设置内存 -> 导致内存溢出
     */
    public static void moreThanHeapSize() {
        List list = new ArrayList<>(0);
        int count = 0;
        try {
            while (true) {
                list.add(new User());
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("HeapMemoryOutOfExample.moreThanHeapSize: 执行次数" + count);
        }

        /**
         * # 运行结果: java.lang.OutOfMemoryError: Java heap space (堆内存溢出)
         *
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * 	at com.calvin.jvm.structure.example.HeapMemoryOutOfExample$User.<init>(HeapMemoryOutOfExample.java:25)
         * 	at com.calvin.jvm.structure.example.HeapMemoryOutOfExample.moreThanHeapSize(HeapMemoryOutOfExample.java:63)
         * 	at com.calvin.jvm.structure.example.HeapMemoryOutOfExample.main(HeapMemoryOutOfExample.java:82)
         * HeapMemoryOutOfExample.moreThanHeapSize: 执行次数6
         */
    }


    /**
     * 案例三: 一次性查询MySQL大数据表，超过设置内存 -> 导致内存溢出
     */
    private static void findBigDataByMySQL() {
        // 伪代码，大数据
        // List<User> bigDataUsers = listByUser();
        // 使用该对象进行业务逻辑处理
    }


    /**
     * 案例四: 使用Jmap 内存分析, 运行参数加上 -XX:+PrintGCDetails 输出GC回收日志
     */
    private static void heapAnalysisByJmap() throws InterruptedException {
        System.out.println("开始执行第一步");
        // 休眠30秒
        Thread.sleep(30000);
        // 申请 10MB 内存
        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println("执行到第二步");
        // 休眠30秒
        Thread.sleep(30000);
        bytes = null;
        // 通知GC进行回收
        System.gc();
        System.out.println("执行到第三步");
        Thread.sleep(1000 * 10000);

        /**
         * # 开始执行第一步
         *   Heap Usage:
         *      PS Young Generation
         *      Eden Space:
         *      capacity = 134217728 (128.0MB)
         *      -- 初始值为 36MB
         *      used     = 37845736 (36.092506408691406MB)
         *      free     = 96371992 (91.9074935913086MB)
         *      28.19727063179016% used
         *      From Space:
         *      capacity = 22020096 (21.0MB)
         *      used     = 0 (0.0MB)
         *      free     = 22020096 (21.0MB)
         *      0.0% used
         *      To Space:
         *      capacity = 22020096 (21.0MB)
         *      used     = 0 (0.0MB)
         *      free     = 22020096 (21.0MB)
         *      0.0% used
         *      PS Old Generation
         *              capacity = 356515840 (340.0MB)
         *      used     = 0 (0.0MB)
         *      free     = 356515840 (340.0MB)
         *      0.0% used
         *
         *  # 执行到第二步
         *    Heap Usage:
         *    PS Young Generation
         *    Eden Space:
         *       capacity = 134217728 (128.0MB)
         *       -- 增加了10MB后，为 46MB
         *       used     = 48331512 (46.09252166748047MB)
         *       free     = 85886216 (81.90747833251953MB)
         *       36.009782552719116% used
         *    From Space:
         *       capacity = 22020096 (21.0MB)
         *       used     = 0 (0.0MB)
         *       free     = 22020096 (21.0MB)
         *       0.0% used
         *    To Space:
         *       capacity = 22020096 (21.0MB)
         *       used     = 0 (0.0MB)
         *       free     = 22020096 (21.0MB)
         *       0.0% used
         *    PS Old Generation
         *       capacity = 356515840 (340.0MB)
         *       used     = 0 (0.0MB)
         *       free     = 356515840 (340.0MB)
         *       0.0% used
         *
         *  # 执行到第三步
         *    Heap Usage:
         *    PS Young Generation
         *    Eden Space:
         *       capacity = 134217728 (128.0MB)
         *       -- GC 回收后，为 2.5MB
         *       used     = 2684376 (2.5600204467773438MB)
         *       free     = 131533352 (125.43997955322266MB)
         *       2.0000159740448% used
         *    From Space:
         *       capacity = 22020096 (21.0MB)
         *       used     = 0 (0.0MB)
         *       free     = 22020096 (21.0MB)
         *       0.0% used
         *    To Space:
         *       capacity = 22020096 (21.0MB)
         *       used     = 0 (0.0MB)
         *       free     = 22020096 (21.0MB)
         *       0.0% used
         *    PS Old Generation
         *       capacity = 356515840 (340.0MB)
         *       used     = 2883704 (2.7501144409179688MB)
         *       free     = 353632136 (337.24988555908203MB)
         *       0.8088571885052849% used
         */

    }


    /**
     * 案例四: 使用Jvisualvm 内存分析, 运行参数加上 -XX:+PrintGCDetails 输出GC回收日志
     */
    private static void heapAnalysisByJvisualvm() throws InterruptedException {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // 大约使用 100MB
            users.add(new User());
        }
        Thread.sleep(1000000);
    }


    /**
     * 主方法 设置运行程序内存为2MB -Xmx2m
     *
     * @param args arg 参数
     */
    public static void main(String[] args) throws InterruptedException {

        // // 案例一: 递归深度长 -> 导致内存溢出
        // recursionDeepLong();
        // // 案例二: 对象设置指定内存，超过设置内存 -> 导致内存溢出
        // moreThanHeapSize();
        // // 案例三: 一次性查询MySQL大数据表，超过设置内存 -> 导致内存溢出
        // findBigDataByMySQL();

        /**
         * 如何暂时直接解决以上案例 ？
         * 答: 将内存扩大
         */

        /**
         * 扩大后，还是无法解决问题，如何分析堆内存溢出？
         * 答:
         * 方式一：
         *   1. Jps 查看JAVA进程
         *   2. Jmap 工具 查看堆内存占用情况
         *   ```shell
         *   $ jmap -heap 进程ID
         *    ```
         * 方式二:
         *  1. 通过图形化界面查看内存信息 Jvisualvm 或 Jconsole
         */

        // 案例四: 通过Jmap分析，堆内存使用情况
        // heapAnalysisByJmap();
        // 案例五: 通过Jvisualvm分析，堆内存使用情况
        heapAnalysisByJvisualvm();

        // moreThanHeapSize();


    }


}
