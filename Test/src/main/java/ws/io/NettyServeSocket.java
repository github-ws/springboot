package ws.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import java.time.LocalDateTime;

public class NettyServeSocket {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup jieshou=new NioEventLoopGroup();
        EventLoopGroup handle=new NioEventLoopGroup();
        ServerBootstrap s=new ServerBootstrap();
        s.group(jieshou,handle)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LineBasedFrameDecoder(1024));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(ctx.channel().remoteAddress()+"我收到了"+msg);
                                ctx.writeAndFlush(LocalDateTime.now()+"server发送消息\n");
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                System.out.println("exception");
                                ctx.close();
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = s.bind(8888).sync();
        System.out.println("start server");
        channelFuture.channel().closeFuture().sync();
        jieshou.shutdownGracefully();
        handle.shutdownGracefully();
    }
}
