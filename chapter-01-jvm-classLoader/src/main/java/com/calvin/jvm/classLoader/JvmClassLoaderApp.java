package com.calvin.jvm.classLoader;

import com.calvin.jvm.classLoader.util.JvmClassLoaderUtils;


/**
 * jvm类加载器应用
 *
 * @author Calvin
 * @date 2023/4/6
 * @since v1.0.0
 */
public class JvmClassLoaderApp {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 读取: 启动类-加载器
        JvmClassLoaderUtils.bootstrapClassLoader();
        // 读取：扩展类-加载器
        JvmClassLoaderUtils.extClassLoader();
        // 读取：应用类-加载器
        JvmClassLoaderUtils.appClassLoader();
    }

}
