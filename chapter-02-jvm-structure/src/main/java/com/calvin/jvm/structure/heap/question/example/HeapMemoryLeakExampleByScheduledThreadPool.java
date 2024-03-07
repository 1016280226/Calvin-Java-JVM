package com.calvin.jvm.structure.heap.question.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 案例: 对数据库中的信用卡进行贷款评估。
 * - 该程序执行一定时间后可能频繁FullGC，然后OOM。
 * - 启动参数: -XX:+PrintGCDetails -Xms200M -Xmx200M
 *
 * @author calvin
 * @date 2024/03/06
 */
public class HeapMemoryLeakExampleByScheduledThreadPool {

    /**
     * 定时线程池
     */
    public static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
            50,
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    /**
     * 卡信息
     * <p>
     * - 模拟一张行用卡，里面有个空的m()
     *
     * @author calvin
     * @date 2024/03/06
     */
    public static class CardInfo {

        /**
         * 价格
         */
        BigDecimal price = BigDecimal.ZERO;

        /**
         * 名字
         */
        String name = "张三";

        /**
         * 年龄
         */
        long age = 30;

        /**
         * 出生日期
         */
        Date birthDate = new Date();

        /**
         * 米
         */
        public void m() {

        }
    }



    /**
     * main方法
     *
     * @param args 参数
     * @throws Exception 异常
     */
    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        // 死循环 每次去100条数
        for (; ; ) {
            modFitForFix();
            Thread.sleep(100);
        }
    }

    /**
     * 模型匹配(内存泄漏的代码)
     */
    public static void modFit() {
        // 取100条数据
        List<CardInfo> list = getAllCard();

        // 任务中引用了外部的 cardInfo 对象。由于 cardInfo 是在 getAllCard() 方法中创建的对象
        list.forEach(cardInfo -> {
            // to do something
            executor.scheduleWithFixedDelay(() -> {
                // ScheduledThreadPoolExecutor 会持有对这些任务的引用，导致这些 cardInfo 对象无法被释放，从而导致内存泄漏。
                cardInfo.m();
            }, 2, 3, TimeUnit.SECONDS);
        });

    }


    /**
     * 模型匹配(修复内存泄漏的代码)
     */
    public static void modFitForFix() {
        // 取100条数据
        List<CardInfo> list = getAllCard();
        list.forEach(cardInfo -> {
            // 为了解决这个问题，可以在 executor.scheduleWithFixedDelay() 方法中使用局部变量来引用 cardInfo 对象，而不是直接引用外部对象。这样可以确保任务执行完后， cardInfo 对象可以被垃圾回收释放内存。
            CardInfo localCardInfo = cardInfo; // 使用局部变量引用外部对象
            executor.scheduleWithFixedDelay(() -> {
                localCardInfo.m();
            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    /**
     * 每次从数据库中获得100个行用卡信息
     */
    public static List<CardInfo> getAllCard() {
        List<CardInfo> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new CardInfo());
        }
        return list;
    }


}
