package com.calvin.hotDeploy;

/**
 * 类文件
 *
 * @author Calvin
 * @date 2023/4/14
 * @since v1.0.0
 */
public class ClassFile {

    /**
     * 类名称
     */
    private String className;

    /**
     * Class
     */
    private Class c;

    /**
     * 最新被修改时间
     */
    private Long latestUpdateTime;

    /**
     * 类文件
     *
     * @param className        类名
     * @param latestUpdateTime 最新更新时间
     */
    public ClassFile(String className, Long latestUpdateTime) {
        this.className = className;
        this.latestUpdateTime = latestUpdateTime;
    }

    /**
     * 类文件
     *
     * @param className        类名
     * @param c                Class
     * @param latestUpdateTime 最新更新时间
     */
    public ClassFile(String className, Class c, Long latestUpdateTime) {
        this.className = className;
        this.c = c;
        this.latestUpdateTime = latestUpdateTime;
    }


    /**
     * 获取-类名
     *
     * @return {@link String}
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置-类名
     *
     * @param className 类名
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取-Class
     *
     * @return {@link Class}
     */
    public Class getC() {
        return c;
    }

    /**
     * 设置-Class
     *
     * @param c c
     */
    public void setC(Class c) {
        this.c = c;
    }

    /**
     * 获取-最新更新时间
     *
     * @return {@link Long}
     */
    public Long getLatestUpdateTime() {
        return latestUpdateTime;
    }

    /**
     * 设置-最新更新时间
     *
     * @param latestUpdateTime 最新更新时间
     */
    public void setLatestUpdateTime(Long latestUpdateTime) {
        this.latestUpdateTime = latestUpdateTime;
    }

}
