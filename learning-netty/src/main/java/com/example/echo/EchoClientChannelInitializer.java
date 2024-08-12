package com.example.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();

        // 字符串解码和编码
        p.addLast("decoder", new StringDecoder());
        p.addLast("encoder", new StringEncoder());

        p.addLast("handler", new EchoClientHandler());
    }
}
