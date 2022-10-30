import java.net.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws SocketException, UnknownHostException {

        // getNetworkInterfaces() вернусь список всех интерфейсов
        ArrayList<NetworkInterface> interfaces = Collections.list(
                NetworkInterface.getNetworkInterfaces());

        System.out.println("Информация о сетевых интерфейсах...\n");
        System.out.println("Поднятые интерфейсы:");
        for (NetworkInterface iface : interfaces) {
            if (iface.isUp()) {
                // getName() method
                System.out.println("Имя интерфейса: " + iface.getName());
                // getDisplayName() method
                System.out.println("Отображаемое имя интерфейса: " + iface.getDisplayName());
                // isLoopback() method
                System.out.println("\tloopback: " + iface.isLoopback());
                // isVirtual() method
                System.out.println("\tvirtual: " + iface.isVirtual() + "\n");
            }
        }
        System.out.println("Не поднятые интерфейсы:");

        for (NetworkInterface iface : interfaces) {
            if (!iface.isUp()) {
                // getName() method
                System.out.println("Имя интерфейса: " + iface.getName());
                // getDisplayName() method
                System.out.println("Отображаемое имя интерфейса: " + iface.getDisplayName());
                // isLoopback() method   !!!!
                System.out.println("\tloopback: " + iface.isLoopback());
                // isVirtual() method  !!!!!
                System.out.println("\tvirtual: " + iface.isVirtual() + "\n");
            }
        }
    }
}
