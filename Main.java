import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in_url  =new Scanner(System.in);
        System.out.println("URL:");
        String user_URL  = in_url.nextLine();
        URL address = new URL(user_URL);
        try {
            URLConnection connection = address.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("***Соединение установлено***");
            System.out.println("Размер ресурса в байтах - " + connection.getContentLength());
            System.out.println("Тип содержимого в запросе - " + connection.getContentType());
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        }catch (Exception e) {
            System.out.println("***Соединение не установлено***");
        }
    }
}
