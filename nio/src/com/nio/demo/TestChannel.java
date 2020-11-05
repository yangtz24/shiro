package com.nio.demo;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.SortedMap;

/**
 * @ClassName: TestChannel
 * @Description: 通道
 * Channel:用于源节点雨目标节点的连接，负责缓冲区的数据传输。本身不存储数据
 * Channel接口实现类：
 * --FileChannel
 * --SocketChannel
 * --ServerSocketChannel
 * --DatagramChannel
 * <p>
 * 获取通道：
 * 1. getChannel()
 * 2. open()  jdk7 静态方法
 * 3. newByteChannel()  jdk7的Files工具类
 * <p>
 * 通道之间数据传输：
 * 1. transferTo()
 * 2. transferFrom()
 * <p>
 * 分散 与 聚集
 * 分散读取：将通道中的数据分散到多个缓冲区
 * 聚集写入：将多个缓冲区数据聚集到通道中
 * <p>
 * 字符集：
 * 编码：字符串 -> 字节数组
 * 解码： 字节数组 -> 字符串
 * @author: yangtianzeng
 * @date: 2020/3/22 13:16
 */
public class TestChannel {

    @Test
    public void test6() throws CharacterCodingException {
        final Charset charset = Charset.forName("GBK");
        //获取编码器
        final CharsetEncoder encoder = charset.newEncoder();
        //获取解码器
        final CharsetDecoder decoder = charset.newDecoder();

        final CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("哈哈哈哈");
        charBuffer.flip();

        //编码
        final ByteBuffer eb = encoder.encode(charBuffer);

        for (int i = 0; i < charBuffer.length(); i++) {
            System.out.println(eb.get(i));
        }

        //解码
        eb.flip();
        final CharBuffer db = decoder.decode(eb);
        System.out.println(db.toString());

        System.out.println("---------------------------------------------");

        final Charset charset1 = Charset.forName("UTF-8");
        final CharBuffer decode = charset1.decode(eb);
        decode.flip();
        System.out.println(decode.toString());


    }

    @Test
    public void test5() {
        final SortedMap<String, Charset> map = Charset.availableCharsets();
        for (Map.Entry<String, Charset> charsetEntry : map.entrySet()) {
            System.out.println(charsetEntry.getKey() + "=" + charsetEntry.getValue());
        }
    }

    @Test
    public void test4() throws IOException {
        final RandomAccessFile rw = new RandomAccessFile("1.txt", "rw");

        //获取通道
        final FileChannel channel = rw.getChannel();

        //分配到指定大小的缓冲区
        final ByteBuffer buf1 = ByteBuffer.allocate(100);
        final ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("**************************");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));


        //聚集写入
        final RandomAccessFile rw2 = new RandomAccessFile("2.txt", "rw");
        final FileChannel channel2 = rw2.getChannel();

        channel2.write(bufs);

        channel.close();
        channel2.close();
    }

    @Test
    public void test3() throws IOException {
        //使用直接缓冲区完成文件复制（内存映射文件）
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }


    @Test
    public void test2() throws IOException {
        //使用直接缓冲区完成文件复制（内存映射文件）
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

        //内存文件映射（只有Byte可以）
        MappedByteBuffer inMapperBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接在缓冲区中读写
        byte[] bytes = new byte[inMapperBuf.limit()];
        inMapperBuf.get(bytes);
        outMapperBuf.put(bytes);

        inChannel.close();
        outChannel.close();

    }

    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            //利用通道完成文件复制
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //将通道中的数据写入缓冲区
            while (inChannel.read(buffer) != -1) {
                buffer.flip();//切换读的模式
                outChannel.write(buffer);//将缓冲区数据写入通道
                buffer.clear();//清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
                if (inChannel != null) inChannel.close();
                if (outChannel != null) outChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
