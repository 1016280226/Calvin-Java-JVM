package com.calvin.hotDeploy;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 热部署类插件
 *
 * @author Calvin
 * @date 2023/4/14
 * @since v1.0.0
 * @description 主要作用: 是如何加载最新变化的class文件。
 *
 */

public class HotDeployPlugins {

    /**
     * 存放Class文件
     */
    private ConcurrentHashMap<String, ClassFile> nameToClassMap = new ConcurrentHashMap<>();

    /**
     * 文件夹路径
     */
    @Getter
    @Setter
    private String fileDirPath;

    /**
     * 类包
     */
    @Getter
    @Setter
    private String classPackage;


    HotDeployPlugins(String fileDirPath, String classPackage) {
        this.fileDirPath = fileDirPath;
        this.classPackage = classPackage;
    }

    /**
     * 监听
     *
     * @decription 实现思路:
     */
    public void listening() {
        // 1.多线程(线程池): 定时监听 / 循环监听 文件变化
        Thread thread = new Thread(() -> {
            // 循环监听
            while (true) {

                // 2.读取: Class文件列表
                File fileDir = new File(fileDirPath);
                File[] classFiles = fileDir.listFiles();

                if (null == classFiles) {
                    continue;
                }
                // 3.遍历：存入Map
                for (File classFile : classFiles) {

                    String fileName = classFile.getName();
                    if (StringUtils.isBlank(fileName)) {
                        continue;
                    }

                    long l = classFile.lastModified();
                    String className = classPackage + "." + fileName.replace(".class", "");

                    ClassFile findClassFile = nameToClassMap.get(className);
                    // 未被加载过,存入MAP
                    if (findClassFile == null) {
                        nameToClassMap.put(className, new ClassFile(className, l));
                    }

                    // 已被加载过，通过时间判断是否被修改过，设置最新时间、class文件类
                    else if (!findClassFile.getLatestUpdateTime().equals(l)) {
                        findClassFile.setLatestUpdateTime(l);

                        MyClassLoader classLoader = new MyClassLoader(classFile);
                        try {
                            Class<?> aClass = classLoader.loadClass(className);
                            Object o = aClass.newInstance();
                            System.out.println("class 文件已发送了变化: " + o.getClass());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // 预防CPU过度飙高
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        HotDeployPlugins hotDeployPlugins = new HotDeployPlugins(
                "/Users/calvin/学习/Jvm 虚拟机/calvin-java-jvm/chapter-01-jvm-classLoader-hot-deploy/target/test-classes/com/calvin/hotDeploy",
                "com.calvin.hotDeploy");
        hotDeployPlugins.listening();
    }

}
