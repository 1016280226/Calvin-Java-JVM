package com.calvin.jvm.classLoader.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义: 我的类加载器
 *
 * @author calvin
 * @date 2023/04/07
 */
@Data
@AllArgsConstructor
public class MyClassLoader extends ClassLoader {

    /** 文件 */
    private File file;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 从文件中读取去class文件 (可以修改网络传输文件)
            byte[] data = getClassFileBytes(this.file);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件字节流
     *
     * @param file 文件
     * @return {@link byte[]}
     * @throws Exception 异常
     */
    private byte[] getClassFileBytes(File file) throws Exception {
        // 采用NIO读取
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileC = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel outC = Channels.newChannel(baos);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            int i = fileC.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            outC.write(buffer);
            buffer.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

}