package com.calvin.jvm.gc.algorithm;

/**
 * 引用计数对象
 *
 * @author calvin
 * @date 2023/10/08
 */
class ReferenceCountedObject {
    private int referenceCount;

    /**
     * 引用计数对象
     */
    public ReferenceCountedObject() {
        referenceCount = 0;
    }

    /**
     * 添加引用
     */
    public void addReference() {
        referenceCount++;
    }

    /**
     * 删除引用
     */
    public void removeReference() {
        referenceCount--;
    }

    /**
     * 获取引用计数
     *
     * @return int
     */
    public int getReferenceCount() {
        return referenceCount;
    }
}

/**
 * 引用计数示例
 *
 * @author calvin
 * @date 2023/10/08
 */
public class ReferenceCountingExample {

    /**
     * 主要
     *
     * @param args arg 参数
     */
    public static void main(String[] args) {

        ReferenceCountedObject obj1 = new ReferenceCountedObject();
        // 增加引用计数
        obj1.addReference();

        // obj2 引用 obj1
        ReferenceCountedObject obj2 = obj1;
        // 增加引用计数
        obj2.addReference();
        // 输出引用计数
        System.out.println("obj1 reference count: " + obj1.getReferenceCount());
        // 减少引用计数
        obj1.removeReference();
        System.out.println("obj1 reference count: " + obj1.getReferenceCount());

        // 减少引用计数
        obj2.removeReference();
        System.out.println("obj1 reference count: " + obj1.getReferenceCount());
    }

    /**
     * 输出结果:
     *
     * obj1 reference count: 2
     * obj1 reference count: 1
     * obj1 reference count: 0
     */
}