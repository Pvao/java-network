package src.main.java;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        final var server = HttpServer.create(new InetSocketAddress("localhost", 8081), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.createContext("/example", new ExampleHTTPHandler());
        server.start();
    }
}
