package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Server address:");
            Scanner userAddress = new Scanner(System.in);
            String Address = userAddress.nextLine();
            System.out.println("Server port:");
            Scanner userPort = new Scanner(System.in);
            int Port = userPort.nextInt();

            var client = SocketChannel.open(new InetSocketAddress(Address, Port));
            var buffer = ByteBuffer.wrap("Hello".getBytes());
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            var response = new String(buffer.array()).trim();
            System.out.println("Response: " + response);
            buffer.clear();
            //  client.close();

            if (response!=null) {
                //  client = SocketChannel.open(new InetSocketAddress(Address, Port));

                var  buffer2 = ByteBuffer.wrap("Stop".getBytes());
                client.write(buffer2);
                //client.read(buffer);
                // response = new String(buffer.array()).trim();
                //  System.out.println("Response: " + response);
                buffer2.clear();
                client.close();
            } else {
                System.out.println("the answer is not correct");
            };


        }catch (Exception e) {
            System.out.println("Error");
        }
    }
}