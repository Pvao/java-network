package client.src.main.java;

import java.io.IOException;
import java.net.*;
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

           DatagramSocket socket = new DatagramSocket();
           InetAddress address = InetAddress.getByName(Address);
           byte[] buf = "Hello".getBytes();
           DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Port);
           socket.send(packet);
           System.out.println("'Hello' sended");

           packet = new DatagramPacket(buf, buf.length);
           socket.receive(packet);
           while (true) {
               String received = new String(packet.getData(), 0, packet.getLength());
               System.out.println("Response Server: " + received);
               if (received != null) {
                   buf = "Stop".getBytes();
                   DatagramPacket packet2 = new DatagramPacket(buf, buf.length, address, Port);
                   socket.send(packet2);
                   socket.close();
                   break;
               }
           }
       }catch (Exception e) {
           System.out.println("Error");
       }
    }
}
