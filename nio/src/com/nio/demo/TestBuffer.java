package com.nio.demo;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @ClassName: TestBuffer
 * @Description: 缓冲区(buffer) 用于存储不同类型的数据
 *      ByteBuffer/CharBuffer/ShortBuffer/IntBuffer/LongBuffer/FloatBuffer/DoubleBuffer
 *      通过  allocate()方法获取缓冲区
 *     缓冲区两个核心方法
 *              1. put(): 存入数据到缓冲区
 *              2. get(): 获取缓冲区的数据
 *     缓冲区核心属性：
 *          capacity：容量，表示缓冲区最大存储量，一旦声明不能改变
 *          limit: 界限，表示缓冲区可操作数据的大小
 *          position: 位置，表示正在操作数据的位置
 *          mark： 标记位置   记录position位置。可通过 reset() 恢复到mark的位置
 *            0 <= mark <= position <=limit <= capacity
 * @author: yangtianzeng
 * @date: 2020/3/22 10:46
 */
public class TestBuffer {

    String str = "abcde";
    //分配一个指定大小的缓冲区
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    @Test
    public void test2() {
        buffer.put(str.getBytes());

        buffer.flip();

        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0 , 2));
        System.out.println(buffer.position());

        //标记
        final Buffer mark = buffer.mark();
        System.out.println(mark);

        buffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2 , 2));
        System.out.println(buffer.position());


        //reset() 恢复到  mark 位置
        final Buffer reset = buffer.reset();
        System.out.println(reset);
        System.out.println(buffer.position());

        //判断缓冲区是否还有数据
        if(buffer.hasRemaining()) {
            //获取剩余数据
            System.out.println(buffer.remaining());
        }

    }





    @Test
    public void test1() {
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("-----------------------------------------------------");

        //存数据到缓冲区  put()
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("-----------------------------------------------------");

        //切换到读取数据  flip()
        Buffer flip = buffer.flip();

        System.out.println(flip.toString());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("-----------------------------------------------------");

        //读取数据  get()
//        byte b = buffer.get();
        ByteBuffer b = buffer.get(new byte[buffer.limit()]);

        System.out.println(b);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("-----------------------------------------------------");

        //可重复读  rewind()
        Buffer rewind = buffer.rewind();

        System.out.println(rewind);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("-----------------------------------------------------");

        //清空缓冲区   clear():  数据并么有清空，只是被遗忘，position limit 又回到初始位置
        Buffer clear = buffer.clear();

        System.out.println(clear);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }
}
