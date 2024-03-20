package com.cqnews.cloud.echo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {


    // 保存客户端的 Channel 对象
    private static final Map<String, Channel> clientChannels = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端连接建立时触发，保存客户端的 Channel 对象
        String clientId = getClientId(ctx.channel());
        clientChannels.put(clientId, ctx.channel());
        System.out.println("Client connected: " + clientId);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 客户端连接断开时触发，移除保存的客户端 Channel 对象
        String clientId = getClientId(ctx.channel());
        clientChannels.remove(clientId);
        System.out.println("Client disconnected: " + clientId);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常时的处理逻辑
        cause.printStackTrace();
        ctx.close();
    }

    // 获取客户端的唯一标识，这里使用远程地址
    private String getClientId(Channel channel) {
        return channel.remoteAddress().toString();
    }
    @Override
    protected void channelRead0(ChannelHandlerContext cc, String s) throws Exception {
        String clientId = getClientId(cc.channel());
        System.out.println("收到客户端"+clientId+"的消息:"+s);
        cc.writeAndFlush("服务端收到消息了 我响应了");
    }



}
