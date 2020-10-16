package ws.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

public class JavaNioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector=Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost",8888));
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey:selectionKeys){
                if(selectionKey.isConnectable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                    System.out.println("连接成功");
                    ByteBuffer buffer=ByteBuffer.wrap( "df很好的".getBytes());
                    channel.write(buffer);
                    new Thread(() -> {
                        while(true){
                            Scanner scanner=new Scanner(System.in);
                            String next = scanner.next();
                            buffer.clear();
                            buffer.put(next.getBytes());
                            buffer.flip();
                            try {
                                channel.write(buffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }else if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer=ByteBuffer.allocate(100);
                    channel.read(buffer);
                    buffer.flip();
                    System.out.println(Charset.forName("utf-8").decode(buffer));
                    buffer.clear();
                }
            }
            selectionKeys.clear();
        }
    }
}
