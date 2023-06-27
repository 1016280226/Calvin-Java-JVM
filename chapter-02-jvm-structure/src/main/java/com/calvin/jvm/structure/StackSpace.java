package com.calvin.jvm.structure;

/**
 * 栈空间
 *
 *
 * @author Calvin
 * @date 2023/4/25
 * @since v1.0.0
 */
public class StackSpace {

    /**
     * Main 主方法 (栈帧)
     *
     * @param args 参数
     */
    public static void main(String[] args) {
       /*
        * 1 -> 为操作数栈
        *   - 底层汇编指令 (iconst_1) JVM采用 bipush 指令将常量压入栈中
        *
        * i -> 为局部变量
        *   - 存放在"局部变量表"
        *   - 单独开辟一个"变量槽"
        *   - 底层汇编指令 (iconst_1) JVM采用 bipush 指令将常量压入栈中
        */
       int i = 1;
       int j = i + 1;

    }
}

/**
 * 通过 javap -c -v StackSpace.class 命令，查询Class文件汇编后指令
 *
 * public class com.calvin.jvm.structure.StackSpace
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #3.#19         // java/lang/Object."<init>":()V
 *    #2 = Class              #20            // com/calvin/jvm/structure/StackSpace
 *    #3 = Class              #21            // java/lang/Object
 *    #4 = Utf8               <init>
 *    #5 = Utf8               ()V
 *    #6 = Utf8               Code
 *    #7 = Utf8               LineNumberTable
 *    #8 = Utf8               LocalVariableTable
 *    #9 = Utf8               this
 *   #10 = Utf8               Lcom/calvin/jvm/structure/StackSpace;
 *   #11 = Utf8               main
 *   #12 = Utf8               ([Ljava/lang/String;)V
 *   #13 = Utf8               args
 *   #14 = Utf8               [Ljava/lang/String;
 *   #15 = Utf8               i
 *   #16 = Utf8               I
 *   #17 = Utf8               SourceFile
 *   #18 = Utf8               StackSpace.java
 *   #19 = NameAndType        #4:#5          // "<init>":()V
 *   #20 = Utf8               com/calvin/jvm/structure/StackSpace
 *   #21 = Utf8               java/lang/Object
 * {
 *   public com.calvin.jvm.structure.StackSpace();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 11: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/calvin/jvm/structure/StackSpace;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=1, locals=2, args_size=1
 *          0: iconst_1
 *          1: istore_1
 *          2: return
 *       LineNumberTable:
 *         line 26: 0
 *         line 30: 2
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       3     0  args   [Ljava/lang/String;
 *             2       1     1     i   I
 * }
 */
