package server.src.main.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class server {
    public static void main(String[] args) throws IOException {
        byte[] buf = new byte[256];
        try{
        DatagramSocket socket = new DatagramSocket(53735);

        while (true)
         {
             DatagramPacket packet = new DatagramPacket(buf, buf.length);
             socket.receive(packet);
             InetAddress address = packet.getAddress();
             int port = packet.getPort();
             String received = new String(packet.getData(), 0, packet.getLength());
             if (received.equals("Stop")){
                 socket.close();
                 break;
             }
             System.out.println("Server Received: " + received);
             packet = new DatagramPacket(buf, buf.length, address, port);
             socket.send(packet);
         }
      } catch (Exception e) {
            System.out.println("Error");
            }
    }
}
