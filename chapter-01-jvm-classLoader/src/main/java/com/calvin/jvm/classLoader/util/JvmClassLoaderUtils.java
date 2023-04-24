package com.calvin.jvm.classLoader.util;

import java.util.Arrays;
import java.util.List;

/**
 * jvm类加载器-工具
 *
 * @author Calvin
 * @date 2023/4/7
 * @since v1.0.0
 */
public class JvmClassLoaderUtils {

    /**
     * 获取类加载器
     *
     * @param c c
     * @return {@link ClassLoader}
     */
    public static <T> ClassLoader getClassLoader(Class<T> c) {
        return c.getClassLoader();
    }

    /**
     * 启动类-加载器
     */
    public static void bootstrapClassLoader() {
        // 获取 ${JAVA_HOME}/jre/lib 或 classes 目录
        String property = System.getProperty("sun.boot.class.path");
        List<String> paths = Arrays.asList(property.split(":"));
        paths.forEach(path -> System.out.println("启动类-加载器目录：" + path));
    }


    /**
     * 扩展类-加载器
     */
    public static void extClassLoader() {
        // 获取 ${JAVA_HOME}/jre/lib/ext 目录
        String property = System.getProperty("java.ext.dirs");
        List<String> paths = Arrays.asList(property.split(":"));
        paths.forEach(path -> System.out.println("扩展类-加载器目录：" + path));
    }


    /**
     * 应用类-加载器
     */
    public static void appClassLoader() {
        // 获取 classpath 目录
        /* 区别:
         *  a. mvn工程： 项目/target/classes
         *  b. java工程: 项目/out
         */
        String property = System.getProperty("java.class.path");
        List<String> paths = Arrays.asList(property.split(":"));
        paths.forEach(path -> System.out.println("应用类-加载器目录：" + path));
    }
}