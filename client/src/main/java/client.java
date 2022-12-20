import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpVersion;

public class client {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.start();
            final CountDownLatch latch = new CountDownLatch(1);

            httpClient.newRequest("http://localhost:8081/service?name=example")
                    .version(HttpVersion.HTTP_1_1)
                    .method(HttpMethod.GET)
                    .send(new BufferingResponseListener() {
                        @Override
                        public void onComplete(Result result) {
                            if (result.isSucceeded()) {
                                latch.countDown();
                                System.out.println(result.getResponse());
                            }
                        }
                    });
            latch.await();
        } finally {
            httpClient.stop();
        }
    }


}