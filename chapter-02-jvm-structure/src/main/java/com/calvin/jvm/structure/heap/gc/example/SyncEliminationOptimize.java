package com.calvin.jvm.structure.heap.gc.example;

/**
 * 内存逃逸优化-同步消除方式
 *
 * @author calvin
 * @date 2023/09/13
 */
public class SyncEliminationOptimize {

    /**
     * 计数器
     */
    private int counter = 0;

    /**
     * 增量
     */
    public void increment() {
        // `synchronized`关键字来保护临界区
        synchronized (this) {
            counter++;
        }
    }

    /**
     * 获取计数器
     *
     * @return int
     */
    public int getCounter() {
        // `synchronized`关键字来保护临界区
        synchronized (this) {
            return counter;
        }
    }

    /**
     * 主要
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SyncEliminationOptimize example = new SyncEliminationOptimize();

        for (int i = 0; i < 1000000; i++) {
            // 当前主方法不存在多线程，所以 `synchronized`关键字来保护临界区，编译器可以进行`同步消除优化`，`将锁消`除掉，从而`提高程序的性能`。
            example.increment();
        }

        System.out.println("Counter: " + example.getCounter());
    }
}