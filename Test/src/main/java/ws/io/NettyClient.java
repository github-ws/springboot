package ws.io;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup lianjie = new NioEventLoopGroup();
        Bootstrap s = new Bootstrap();
        s.group(lianjie)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LineBasedFrameDecoder(1024));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("channelActive");
                                ctx.writeAndFlush("hello\n");
                            }

                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(ctx.channel().remoteAddress() + "我收到了" + msg);
                               // ctx.writeAndFlush(LocalDateTime.now() + "client发送消息\n");
                                ctx.close();
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                System.out.println("exception");
                                ctx.close();
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = s.connect("127.0.0.1",8888).sync();
        channelFuture.channel().closeFuture().sync();
        lianjie.shutdownGracefully();
    }
}
