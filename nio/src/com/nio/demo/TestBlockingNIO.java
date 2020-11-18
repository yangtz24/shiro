package com.nio.demo;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName: TestBlockingNIO
 * @Description: 非阻塞式IO
 * <p>
 * NIO完成网咯通信的三个核心：
 * 1. 通道：负责连接
 * SelectableChannel
 * -- SocketChannel
 * -- ServerSocketChannel
 * -- DatagramChannel
 * <p>
 * -- Pipe.SinkChannel
 * -- Pipe.SourceChannel
 * <p>
 * 2. 缓冲区： 负责存储数据
 * 3. 选择器： Selector 多路复用器。用于监控SelectableChannel的IO状况
 * @author: yangtianzeng
 * @date: 2020/3/22 14:44
 */
public class TestBlockingNIO {

    //客户端
    @Test
    public void testClient() throws IOException {
        //获取通道
        final SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9988));

        //分配缓冲区大小
        final ByteBuffer allocate = ByteBuffer.allocate(1024);

        //读取本地文件，发送到服务端
        final FileChannel channel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        while (channel.read(allocate) != -1) {
            allocate.flip();
            channel.write(allocate);
            allocate.clear();
        }

        //关闭通道
        socketChannel.close();
        channel.close();
    }

    //服务端
    @Test
    public void testServer() throws IOException {
        //获取通道
        final ServerSocketChannel socketChannel = ServerSocketChannel.open();
        final FileChannel outChannel = FileChannel.open(Paths.get("11.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //绑定连接
        socketChannel.bind(new InetSocketAddress(9988));

        //获取客户端连接通道
        final SocketChannel accept = socketChannel.accept();

        //接受数据，并保存
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (accept.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        accept.close();
        outChannel.close();
        socketChannel.close();

    }
}
