package com.calvin.jvm.structure.heap.gc.example;

/**
 * 老年代 Major GC / Full GC 触发
 *
 * @author Calvin
 * @date 2023/9/5
 * @since v1.0.0
 */
public class MajorGcOrFullGcTrigger {


    /**
     * MajorGC
     * <p>
     * 当前执行程序:  -Xmx300m  => 最大内存为300MB
     * 触发机制:
     * - 经常会伴随至少一次的Minor GC（但非绝对的，在Parallel Scavenge收集器的收集策略里就有直接进行Major GC的策略选择过程）。
     * - 也就是在老年代空间不足时，会先尝试触发Minor GC。如果之后空间还不足，则触发Major GC。
     * - 不会触发所有垃圾回收, 只会对老年代GC 回收。
     * - Major GC的速度一般会比Minor GC慢10倍以上，STW的时间更长。 如果Major GC 后，内存还不足，就报OOM了。
     *
     *
     * @throws InterruptedException
     */
    public static void majorGc() throws InterruptedException {
        // 当前无法演示Major GC 回收过程。
    }


    /**
     * Full GC
     * <p>
     * 当前执行程序:  -Xmx300m  => 最大内存为300MB
     * 触发机制:
     * - 调用System.gc()时，系统建议执行Full GC，但是不必然执行.
     * - 老年代空间不足.
     * - 方法区空间不足.
     * - 通过Minor GC后进入老年代的平均大小大于老年代的可用内存
     * - 由Eden区、survivor space0（From Space）区向 survivor space1（To Space）区复制时，对象大小大于To Space可用内存，则把该对象转存到老年代，且老年代的可用内存小于该对象大小.
     * - full gc 是开发或调优中尽量要避免的。这样暂停时间会短一些。
     *
     * @throws InterruptedException
     */
    public static void fullGc() throws InterruptedException {
        // A对象 => 占用80MB

        byte[] a = new byte[1024 * 1024 * 80];
        System.out.println("A对象: " + a.length / 1024 / 1024 + "MB");

        // B对象 => 占用220MB
        byte[] b = new byte[1024 * 1024 * 220];
        System.out.println("B对象: " + b.length / 1024 / 1024 + "MB");

        Thread.sleep(1000 * 10);
    }


    /**
     * 主方法
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // majorGc();
        fullGc();
    }




}
