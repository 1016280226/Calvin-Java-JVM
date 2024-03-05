package com.calvin.jvm.structure.metaspace.question.example;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 元空间Oom问题
 *
 *
 * @author calvin
 * @date 2023/10/07
 */
public class MetaspaceOomQuestion extends ClassLoader {

    /**
     * jdk6/7中：
     *  -XX:PermSize=10m -XX:MaxPermSize=10m
     * <p>
     * jdk8中：
     *  -XX:-UseCompressedClassPointers -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     *
     * @param args
     */
    public static void main(String[] args) {
        int j = 0;
        try {
            MetaspaceOomQuestion test = new MetaspaceOomQuestion();
            for (int i = 0; i < 10000; i++) {
                // 创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                // 指明版本号，修饰符，类名，包名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                // 返回byte[]
                byte[] code = classWriter.toByteArray();
                // 类的加载 （自定义类加载）
                test.defineClass("Class" + i, code, 0, code.length);
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }
}