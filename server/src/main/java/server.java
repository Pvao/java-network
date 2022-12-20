import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class server {

    public static void main(String[] args) throws Exception {
        var thread = new QueuedThreadPool();
        thread.setName("server");
        var server = new Server(thread);
        var connector = new ServerConnector(server);
        connector.setPort(8081);
        server.addConnector(connector);
        server.setHandler(new ExampleHTTPHandler());
        server.start();
    }
}