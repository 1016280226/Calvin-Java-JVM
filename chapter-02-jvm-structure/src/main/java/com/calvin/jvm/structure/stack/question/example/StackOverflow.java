package com.calvin.jvm.structure.stack.question.example;

/**
 * 栈溢出
 *
 * @author Calvin
 * @date 2023/10/8
 * @since v1.0.0
 */
public class StackOverflow {

    /**
     * i 计算变量
     */
    private int i;

    /**
     * 加
     */
    public void plus() {
        // 叠加
        i++;
        // 递归过深产生栈溢出
        plus();
    }

    /**
     * 主要
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        StackOverflow stackOverFlow = new StackOverflow();
        try {
            stackOverFlow.plus();
        } catch (Error e) {
            System.out.println("Error:stack length:" + stackOverFlow.i);
            e.printStackTrace();
        }
    }
}
