package ws.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

public class JavaNioSeiver {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server =ServerSocketChannel.open();
        server.configureBlocking(false);
        server.socket().bind(new InetSocketAddress(8888));
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();//阻塞
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    System.out.println("收到客户端连接" + channel.socket().getInetAddress());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer=ByteBuffer.allocate(100);
                    channel.read(buffer);
                    buffer.flip();
                    System.out.println(Charset.forName("utf-8").decode(buffer));
                    buffer.clear();
                    buffer.put("server：收到".getBytes("utf-8"));
                    buffer.flip();
                    channel.write(buffer);
                }
            }
            selectionKeys.clear();
        }
    }
}
