package com.calvin.example;

/**
 * 测试类
 *
 * @author Calvin
 * @date 2023/11/13
 * @since v1.0.0
 */
public class TestClass {

    /**
     * 米
     */
    private int m;

    /**
     * 增加
     *
     * @return int
     */
    public int inc(){
        return m + 1;
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        int inc = testClass.inc();
        System.out.println("TestClass.main => inc: " + inc);
    }

}
