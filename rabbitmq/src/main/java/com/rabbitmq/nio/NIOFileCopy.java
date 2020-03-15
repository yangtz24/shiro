/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:NIOFileCopy.java
 * @Prject: com.rabbitmq.nio
 * @Package: com.rabbitmq.nio
 * @author: yangtianzeng
 * @date: 2020/1/20 17:21
 * @version: V1.0
 */
package com.rabbitmq.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NIOFileCopy
 * @Description: NIO 文件拷贝
 * 两种方式：1.通过管道
 * 2.文件内存映射
 * @author: yangtianzeng
 * @date: 2020/1/20 17:21
 */
public class NIOFileCopy {

    public static final int BUFFER_SIZE = 1024;

    //nio 拷贝
    public static void nioCopyFile(String source, String target) {
        long startTime = System.currentTimeMillis();
        try (FileChannel input = new FileInputStream(new File(source)).getChannel();
             FileChannel output = new FileOutputStream(new File(target)).getChannel()) {
            output.transferFrom(input, 0, input.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("NIO file copy cost %d msc", endTime - startTime));
    }

    //io 拷贝
    public static void copyFile(String source, String target) {
        long startTime = System.currentTimeMillis();
        try (FileInputStream inputStream = new FileInputStream(new File(source));
             FileOutputStream outputStream = new FileOutputStream(new File(target))) {
            final byte[] bytes = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("NIO file copy cost %d msc", endTime - startTime));
    }


    public static void main(String[] args) {
        String source = "e:\\如何设计高可用的微服务架构.pdf";
        String target = "C:\\Users\\robu\\Desktop";
        nioCopyFile(source, target);
        copyFile(source, target);
    }
}
