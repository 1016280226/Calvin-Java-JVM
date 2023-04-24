package com.calvin.jvm.classLoader.util;


import com.calvin.jvm.classLoader.custom.MyClassLoader;
import com.calvin.jvm.classLoader.entity.User;
import lombok.SneakyThrows;
import org.junit.Test;
import sun.misc.Launcher;

import java.io.File;

/**
 * @author Calvin
 * @date 2023/4/7
 * @since v1.0.0
 */
public class JvmClassLoaderTest {

    /**
     * 测试-启动类加载器
     */
    @Test
    public void bootstrapClassLoaderTest() {
        // 1. 判断 java.lang.String 类 rt.jar 包，在哪个类加载器？
        String s = new String();
        ClassLoader classLoader1 = JvmClassLoaderUtils.getClassLoader(s.getClass());
        System.out.println("1.【java.lang.String 类 rt.jar 包, 所属类加载器】: " + (classLoader1 == null ? "BootstrapClassLoader 启动类加载器" : classLoader1));
    }

    /**
     * 测试-应用类-加载器
     */
    @Test
    public void appClassLoaderTest() {
        // 2. 判断 com.calvin.jvm.classLoader.entity.User 类 项目包，在哪个类加载器？
        User user = new User();
        ClassLoader classLoader2 = JvmClassLoaderUtils.getClassLoader(user.getClass());
        System.out.println("2.【com.calvin.jvm.classLoader.entity.User 类 项目包, 所属类加载器】: " + (classLoader2 == null ? "BootstrapClassLoader 启动类加载器" : classLoader2));
    }

    /**
     * 测试-自定义类加载器
     */
    @Test
    public void customClassLoaderTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // 获取文件
        File file = new File("/Users/calvin/学习/Jvm 虚拟机/calvin-java-jvm/chapter-01-jvm-classLoader/remote/classes/com/calvin/jvm/classLoader/entity/User.class");
        // 将文件放入自定义加载器
        MyClassLoader myClassLoader = new MyClassLoader(file);
        // 通过自定义加载器，获取文件对应的Class
        Class<?> aClass = myClassLoader.loadClass("com.calvin.jvm.classLoader.entity.User");
        // 实例化
        Object o = aClass.newInstance();
        System.out.println("3.【通过自定义加载器以文件形式获取Class, 所属类加载器】: " + o.getClass().getClassLoader());
    }


}