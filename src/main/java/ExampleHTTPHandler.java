package src.main.java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class ExampleHTTPHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      switch (exchange.getRequestMethod()){
          case "GET":
              System.out.println("Method GET");
          case "POST":
               handlePostRequest(exchange);
              break;
          default:
              throw new UnsupportedOperationException("Unsupported HTTP method"+exchange.getRequestMethod());
        }
    }

    public void handleRequest (HttpExchange exchange, String response)throws IOException{
        exchange.sendResponseHeaders(200,response.length());

        try(final var responseBody = exchange.getResponseBody()){
            responseBody.write(response.getBytes(StandardCharsets.UTF_8));
            responseBody.flush();
        }
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        var body = resquestBodyToString(exchange);

        var getValue = new HashMap<String, String>();
        Arrays.stream(body.split("&")).forEach(it -> {
            String[] pair = it.split("=", 2);
            getValue.put(pair[0], pair[1]);
        });

        String tagLogin = "login";
        String requestLogin = "java";

        if (requestLogin.equals(getValue.get(tagLogin)) ) {
            try (final var responseBody = exchange.getResponseBody()) {
                String response = "Hello Java";
                exchange.sendResponseHeaders(200, response.length());
                responseBody.write(response.getBytes(StandardCharsets.UTF_8));
                responseBody.flush();
            }
        } else {
            handleRequest(exchange, "Try again");
        }
    }

    private String resquestBodyToString(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }


}
