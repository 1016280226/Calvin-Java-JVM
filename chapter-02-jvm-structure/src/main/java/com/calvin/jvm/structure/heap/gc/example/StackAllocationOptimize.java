package com.calvin.jvm.structure.heap.gc.example;

/**
 * 内存逃逸优化-栈上分配
 *
 * @author calvin
 * @date 2023/09/13
 */
public class StackAllocationOptimize {

    /**
     * 点
     *
     * @author calvin
     * @date 2023/09/13
     */
    private static class Point {
        /**
         * x 轴
         */
        private int x;

        /**
         * y 轴
         */
        private int y;

        /**
         * 点
         *
         * @param x x 轴
         * @param y y 轴
         */
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 获取 x 轴
         *
         * @return int
         */
        public int getX() {
            return x;
        }

        /**
         * 获取 y 轴
         *
         * @return int
         */
        public int getY() {
            return y;
        }
    }


    /**
     * 标点
     *
     * @param x x 轴
     * @param y y 轴
     * @return {@link StackAllocationOptimize.Point}
     */
    private static StackAllocationOptimize.Point createPoint(int x, int y) {
        return new StackAllocationOptimize.Point(x, y);
    }

    /**
     * 主要
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 标点
        StackAllocationOptimize.Point point = createPoint(10, 20);
        System.out.println("Point: (" + point.getX() + ", " + point.getY() + ")");
    }
}