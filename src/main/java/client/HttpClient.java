package src.main.java.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {
    private static void httpPostRequest() throws URISyntaxException, IOException, InterruptedException {
        java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder()
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8081/example"))
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString("login=java&password=qwerty"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println("httpPostRequest:" + responseBody);
    }
}
