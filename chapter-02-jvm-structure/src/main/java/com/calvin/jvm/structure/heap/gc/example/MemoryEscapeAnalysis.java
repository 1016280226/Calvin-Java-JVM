package com.calvin.jvm.structure.heap.gc.example;

/**
 * 内存逃逸分析
 *
 * @author Calvin
 * @date 2023/9/12
 * @since v1.0.0
 */
public class MemoryEscapeAnalysis {


    /**
     * 用户
     */
    private static User user = new User();

    /**
     * 用户
     *
     * @author calvin
     * @date 2023/06/28
     */
    public static class User {
        /**
         * 字节
         */
        private byte[] bytes = new byte[1024 * 1024];
    }


    /**
     * 方法逃逸
     *
     * - 当一个对象在方法中定义之后，作为参数传递到其它方法中.
     *
     * @return {@link User}
     */
    public static User methodEscapeByHeap() {
        User user = new User();
        return user;
    }


    /**
     * 方法未逃逸
     *
     * @return {@link User}
     */
    public static void methodEscapeByStackFrame() {
        User user = new User();
    }


    /**
     * 主方法
     *
     * -XX:+DoEscapeAnalysis (显示开启逃逸分析)
     * -XX:+PrintEscapeAnalysis (查看逃逸分析的筛选结果)
     *
     * @param args 参数列表
     */
    public static void main(String[] args) throws InterruptedException {


        System.out.println("================================= 开始: 方法未逃逸（存放在栈帧）=================================");
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 当前 user 被使用该方法存放在栈帧中
            methodEscapeByStackFrame();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("程序执行的时间:" + (end2 - start2) / 1000d + "秒");
        System.out.println("================================= 结束: 方法未逃逸（存放在栈帧）=================================");


        System.out.println("================================= 开始: 方法逃逸 (存放在堆空间) =================================");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 当前 user 被使用该方法存放在堆空间中，不能在栈上分配
            User user1 = methodEscapeByHeap();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("程序执行的时间:" + (end1 - start1) / 1000d + "秒");
        // Thread.sleep(1000 * 10);
        System.out.println("================================= 结束: 方法逃逸 (存放在堆空间) =================================");


        System.out.println("================================= 开始: 线程逃逸 (存放在堆空间) =================================");
        // 如类变量或实例变量，可能被其它线程访问到；

        Thread threadA = new Thread(() -> {
            User user1 = user;
            System.out.println("threadA 用户信息" + user1);
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadB = new Thread(() -> {
            User user2 = user;
            System.out.println("threadB 用户信息" + user2);
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadA.start();
        threadB.start();
        System.out.println("================================= 开始: 线程逃逸 (存放在堆空间) =================================");

    }

}
