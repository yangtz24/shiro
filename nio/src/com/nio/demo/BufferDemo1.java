package com.nio.demo;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @ClassName: BufferDemo1
 * @Description: 缓冲区 效率
 * <p>
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区， 将缓冲区建立在物理内存中，可提高效率
 * 非直接缓冲区：通过allocate()方法分配直接缓冲区，将缓冲区建立在JVM的内存中
 * @author: yangtianzeng
 * @date: 2020/3/22 11:25
 */
public class BufferDemo1 {

    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);//非直接缓存

        final ByteBuffer direct = ByteBuffer.allocateDirect(1024);//直接缓存

        // isDirect() 判断是否是直接缓存
        System.out.println(buffer.isDirect());//false
        System.out.println(direct.isDirect());//true
    }
}
