package com.calvin.jvm.structure.metaspace;

/**
 * 方法区（元空间）
 *
 * @author Calvin
 * @date 2023/5/11
 * @since v1.0.0
 */
public class Metaspace {

    /**
     * 静态常量
     */
    public static final Integer I = 100;

    /**
     * 字符串常量
     */
    public final String A = "a";

    /**
     * 常量
     */
    public final double b = Math.random();

    /**
     * 主方法信息
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 局部: 字符串常量
        String c = "c";
        System.out.println(c);
    }
}
