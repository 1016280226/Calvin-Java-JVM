package com.calvin.jvm.structure.metaspace;

import java.util.Comparator;

/**
 * 字符串常量池
 *
 * @author Calvin
 * @date 2023/6/12
 * @since v1.0.0
 */
public class StringConstantPool {


    /**
     * 通过 equlas 进行字符串比较
     *
     * @return {@link Boolean}
     */
    public static Boolean stringComparingByEq(String s) {
        String a2 = "a";
        return a2.equals(s);
        /* 源码
          if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }*/
    }


    /**
     * 通过 == 操作符，进行字符串比较
     *
     * @return {@link Boolean}
     */
    public static Boolean stringComparingByEqOperators (String s) {
        String a2 = "a";
        return a2 == s;
    }

    /**
     *  汇编指令:
     *  1. Boolean a3 = stringComparingByEqOperators(new String("a")); ==>
     *  -- #2 -> #45          ==> String a
     *  -- #17 -> #16.#62     ==> Method java/lang/String."<init>":(Ljava/lang/String;)V（构造函数）
     *            #16 -> #61
     *            #62 -> #20:#80
     *  --
     *
     *  -- 第一步: 先查询局部变量表
     *  LocalVariableTable:
     *    Start  Length  Slot  Name   Signature
     *       75      26     3    a3   Ljava/lang/Boolean;
     *
     *  -- 第二步: 查询行数表
     *   LineNumberTable:
     *         line 63: 75
     *
     *  -- 第三步: 查询主方法
     *   Code:
     *       stack=3, locals=4, args_size=1
     *         66: ldc           #2                  // String a
     *         68: invokespecial #17                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
     *         71: invokestatic  #14                 // Method stringComparingByEqOperators:(Ljava/lang/String;)Ljava/lang/Boolean;
     *         74: astore_3
     *         75: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *
     *  -- 第四步: 查询常量池表对应 #数字 进行拼接，这一过程称为"动态链接"
     *  Constant pool:
     *    #1 = Methodref          #19.#44        // java/lang/Object."<init>":()V
     *    #2 = String             #45            // a
     *    #3 = Methodref          #16.#46        // java/lang/String.equals:(Ljava/lang/Object;)Z
     *    #4 = Methodref          #47.#48        // java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
     *    #5 = Methodref          #18.#49        // com/calvin/jvm/structure/StringConstantPool.stringComparingByEq:(Ljava/lang/String;)Ljava/lang/Boolean;
     *    #6 = Fieldref           #50.#51        // java/lang/System.out:Ljava/io/PrintStream;
     *    #7 = Class              #52            // java/lang/StringBuilder
     *    #8 = Methodref          #7.#44         // java/lang/StringBuilder."<init>":()V
     *    #9 = String             #53            // equals 方法:
     *   #10 = Methodref          #7.#54         // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *   #11 = Methodref          #7.#55         // java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
     *   #12 = Methodref          #7.#56         // java/lang/StringBuilder.toString:()Ljava/lang/String;
     *   #13 = Methodref          #57.#58        // java/io/PrintStream.println:(Ljava/lang/String;)V
     *   #14 = Methodref          #18.#59        // com/calvin/jvm/structure/StringConstantPool.stringComparingByEqOperators:(Ljava/lang/String;)Ljava/lang/Boolean;
     *   #15 = String             #60            // == 操作符:
     *   #16 = Class              #61            // java/lang/String
     *   #17 = Methodref          #16.#62        // java/lang/String."<init>":(Ljava/lang/String;)V
     *   #18 = Class              #63            // com/calvin/jvm/structure/StringConstantPool
     *   #19 = Class              #64            // java/lang/Object
     *   #20 = Utf8               <init>
     *   #21 = Utf8               ()V
     *   #22 = Utf8               Code
     *   #23 = Utf8               LineNumberTable
     *   #24 = Utf8               LocalVariableTable
     *   #25 = Utf8               this
     *   #26 = Utf8               Lcom/calvin/jvm/structure/StringConstantPool;
     *   #27 = Utf8               stringComparingByEq
     *   #28 = Utf8               (Ljava/lang/String;)Ljava/lang/Boolean;
     *   #29 = Utf8               s
     *   #30 = Utf8               Ljava/lang/String;
     *   #31 = Utf8               a2
     *   #32 = Utf8               stringComparingByEqOperators
     *   #33 = Utf8               StackMapTable
     *   #34 = Class              #61            // java/lang/String
     *   #35 = Utf8               main
     *   #36 = Utf8               ([Ljava/lang/String;)V
     *   #37 = Utf8               args
     *   #38 = Utf8               [Ljava/lang/String;
     *   #39 = Utf8               a1
     *   #40 = Utf8               Ljava/lang/Boolean;
     *   #41 = Utf8               a3
     *   #42 = Utf8               SourceFile
     *   #43 = Utf8               StringConstantPool.java
     *   #44 = NameAndType        #20:#21        // "<init>":()V
     *   #45 = Utf8               a
     *   #46 = NameAndType        #65:#66        // equals:(Ljava/lang/Object;)Z
     *   #47 = Class              #67            // java/lang/Boolean
     *   #48 = NameAndType        #68:#69        // valueOf:(Z)Ljava/lang/Boolean;
     *   #49 = NameAndType        #27:#28        // stringComparingByEq:(Ljava/lang/String;)Ljava/lang/Boolean;
     *   #50 = Class              #70            // java/lang/System
     *   #51 = NameAndType        #71:#72        // out:Ljava/io/PrintStream;
     *   #52 = Utf8               java/lang/StringBuilder
     *   #53 = Utf8               equals 方法:
     *   #54 = NameAndType        #73:#74        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *   #55 = NameAndType        #73:#75        // append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
     *   #56 = NameAndType        #76:#77        // toString:()Ljava/lang/String;
     *   #57 = Class              #78            // java/io/PrintStream
     *   #58 = NameAndType        #79:#80        // println:(Ljava/lang/String;)V
     *   #59 = NameAndType        #32:#28        // stringComparingByEqOperators:(Ljava/lang/String;)Ljava/lang/Boolean;
     *   #60 = Utf8               == 操作符:
     *   #61 = Utf8               java/lang/String
     *   #62 = NameAndType        #20:#80        // "<init>":(Ljava/lang/String;)V
     *   #63 = Utf8               com/calvin/jvm/structure/StringConstantPool
     *   #64 = Utf8               java/lang/Object
     *   #65 = Utf8               equals
     *   #66 = Utf8               (Ljava/lang/Object;)Z
     *   #67 = Utf8               java/lang/Boolean
     *   #68 = Utf8               valueOf
     *   #69 = Utf8               (Z)Ljava/lang/Boolean;
     *   #70 = Utf8               java/lang/System
     *   #71 = Utf8               out
     *   #72 = Utf8               Ljava/io/PrintStream;
     *   #73 = Utf8               append
     *   #74 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
     *   #75 = Utf8               (Ljava/lang/Object;)Ljava/lang/StringBuilder;
     *   #76 = Utf8               toString
     *   #77 = Utf8               ()Ljava/lang/String;
     *   #78 = Utf8               java/io/PrintStream
     *   #79 = Utf8               println
     *   #80 = Utf8               (Ljava/lang/String;)V
     *
     *
     */

    /**
     * 主方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // equals 方法: 值的对比
        Boolean a1 = stringComparingByEq("a");
        System.out.println("equals 方法: " + a1);

        // == 操作符: 地址引用
        Boolean a2 = stringComparingByEqOperators("a");
        System.out.println("== 操作符: " + a2);


        /** 案例1: new String("a") 和 "a" 字符串常量是否相等？ */
        // new String 对象在开辟新的内存地址，所以和堆内存中字符常量池"a"内存地址不一样
        Boolean a3 = stringComparingByEqOperators(new String("a"));
        System.out.println("== 操作符: " + a3);


        /** 案例2: ab1 和 ab2 字符串常量是否相等？ */
        // "a" + "b" 在 jvm 虚拟机进行过优化后为"ab", 存放在堆内存中的字符常量池中
        String ab1 = "a" + "b";
        String a = "a";
        String b = "b";
        // a + b 在堆内存中的常量池存在 a、b 两个对象， 最终 a + b 通过 new StringBuilder().append("a").append("b").toString() 产生新的对象内存地址。
        String ab2 = a + b;
        // 因为内存地址不一致，所以不等。
        System.out.println("ab1 == ab2: " + (ab1 == ab2));


        /** 案例3: 使用 字符+= 和  StringBuild.append() 字符串链接 那个效率快？ */
        long startMs = System.currentTimeMillis();
        String str = "a";
        for (int i = 0; i < 1000000; i++) {
            // 使用 += 会在堆内存字符常量池不断地添加新的对象，因为String 字符串常量为final 是不可变的，导致了程序变慢。
            str += i;
        }
        long endMs = System.currentTimeMillis();
        System.out.println("+= 用时:" + ((endMs - startMs)/1000) + "秒");


        long startMs1 = System.currentTimeMillis();
        StringBuilder str1 = new StringBuilder("a");
        for (int i = 0; i < 1000000; i++) {
            str1.append(i);
        }
        str1.toString();
        long endMs1 = System.currentTimeMillis();
        System.out.println("StringBuilder.append() 用时:" + ((endMs1 - startMs1)/1000) + "秒");

    }
}
