package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class server {
    public static void main(String[] args) throws IOException {
        var selector = Selector.open();
        var serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 9999));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            var buffer = ByteBuffer.allocate(256);
            selector.select();
            var selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                var key = iter.next();
                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                } else if (key.isReadable()) {
                    answer(buffer, key);
                }
                iter.remove();
            }
        }
    }

    private static void answer(ByteBuffer buffer, SelectionKey key) throws IOException {
        var client = (SocketChannel) key.channel();
        client.read(buffer);
        buffer.flip();
        client.write(buffer);
        var response = new String(buffer.array()).trim();
        System.out.println("Client: " + response);
        if (response.equals("Stop")){
            System.out.println("Stop server");
            System.exit(0);
            buffer.clear();
            client.close();
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        var client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }
}
