package com.calvin.jvm.structure.stack;

/**
 * 栈帧
 *
 * @author Calvin
 * @date 2023/4/25
 * @since v1.0.0
 */
public class StackFrame {


    /**
     * B 方法
     */
    public void b() {
        System.out.println("我是B方法");
    }

    /**
     * A 方法
     */
    public void a() {
        b();
        System.out.println("我是A方法");
    }

    /**
     * 主方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        new StackFrame().a();
    }

}
