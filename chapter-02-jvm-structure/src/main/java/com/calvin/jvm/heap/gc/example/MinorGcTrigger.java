package com.calvin.jvm.heap.gc.example;

/**
 * 新生代 MinorGC 触发
 *
 * @author Calvin
 * @date 2023/8/24
 * @since v1.0.0
 */
public class MinorGcTrigger {


    /**
     * 触发条件: Eden区不足
     *
     * 当前执行程序:  -Xmx300m  => 最大内存为300MB
     *
     * (默认NewRatio=2 => 新生代1/3 老年代2/3)
     *
     * - 新生代占比 300MB * 1/3 = 100MB
     *
     *   (默认SurvivorRatio=8 => 8:1:1)
     *
     *   - eden 区占比： 100MB * 6/8 = 75MB
     *   - SO   区占比： 100MB * 1/8 = 12.5MB
     *   - S1   区占比： 100MB * 1/8 = 12.5MB
     *
     * - 老年代占比: 300MB * 2/3 = 200MB
     */
    public static void eDenSpaceFull() throws InterruptedException {
        // A对象 => 占用25MB
        byte[] a = new byte[1024 * 1024 * 25];
        System.out.println("A对象: " + a.length / 1024 /1024 + "MB") ;

        // 停顿 5 秒
        // Thread.sleep(5000);

        // B对象 => 占用25MB
        byte[] b = new byte[1024 * 1024 * 25];
        System.out.println("B对象: " + b.length / 1024 /1024 + "MB");

        // C对象 => 占用25MB
        byte[] c = new byte[1024 * 1024 * 25];
        System.out.println("C对象: " + c.length / 1024 /1024 + "MB");

        // 停顿 5 秒
        Thread.sleep(5000);
    }

    /**
     * 主方法: -Xms300m -Xmx300m -XX:+PrintGCDetails
     *
     * @param args 参数
     * @throws InterruptedException 中断异常
     */
    public static void main(String[] args) throws InterruptedException {
        eDenSpaceFull();
    }

    /**
     * 日志输出
     *
     * A对象: 25MB =>  System.out.println("A对象: " + a.length / 1024 /1024 + "MB") ; 执行完毕， A 对象分配到 eDen 区内存成功。
     * B对象: 25MB =>  System.out.println("B对象: " + b.length / 1024 /1024 + "MB");  执行完毕， B 对象分配到 eDen 区内存成功
     *
     *  byte[] c = new byte[1024 * 1024 * 25]; 执行到该行, 当前新生代eden区内存不足，对象C分配失败，进行 Minor GC 垃圾回收，如下所示:
     *
     * ################ 开始: 当前执行 Minor GC #################
     * [GC (Allocation Failure) [PSYoungGen: 58982K->880K(90624K)] 58982K->52088K(298496K), 0.0222460 secs] [Times: user=0.08 sys=0.01, real=0.02 secs]
     * - GC (Allocation Failure) 表示：
     *   - 分配对象内存失败， 进行 Minor GC 对新对象垃圾回收(包含（SO）区）。
     *   - PSYoungGen: 58982K->880K(90624K) => 新生代 57MB 内存回收后，使用内存为 880k (90624K => 当前新对象内存需要88MB）
     *
     * - Minor GC 特性:
     *   - 非常频繁
     *   - 回收速度快
     *   - 回收无用的对象
     *   - 暂停用户线程，垃圾回收结束后，才会恢复用户线程正常运行。
     *
     * ################ 结束: 当前执行 Minor GC #################
     *
     * C对象: 25MB System.out.println("C对象: " + c.length / 1024 /1024 + "MB")； 执行完毕
     *
     * Heap
     *  PSYoungGen      total 90624K, used 28453K [0x00000007b9b00000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 77824K, 35% used [0x00000007b9b00000,0x00000007bb5ed408,0x00000007be700000)
     *   from space 12800K, 6% used [0x00000007be700000,0x00000007be7dc010,0x00000007bf380000)
     *   to   space 12800K, 0% used [0x00000007bf380000,0x00000007bf380000,0x00000007c0000000)
     *  ParOldGen       total 207872K, used 51208K [0x00000007ad000000, 0x00000007b9b00000, 0x00000007b9b00000)
     *   object space 207872K, 24% used [0x00000007ad000000,0x00000007b0202020,0x00000007b9b00000)
     *  Metaspace       used 3710K, capacity 4536K, committed 4864K, reserved 1056768K
     *   class space    used 410K, capacity 428K, committed 512K, reserved 1048576K
     */


}
