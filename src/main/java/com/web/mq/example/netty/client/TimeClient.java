package com.web.mq.example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

    public void connect(int port, String host) {
        //TODO 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();

         Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSctpServerChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChildChannelHandler());

        try {
            //TODO 发起异步链接操作
            ChannelFuture f = b.connect(host,port).sync();
            //TODO 等待客户端链路关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ChildChannelHandler extends  ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeClientHandler());
        }
    }

    public static void main(String[] args) {
        new TimeClient().connect(8080,"127.0.0.1");
    }
}
