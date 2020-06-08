package main.requests;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import static main.utlis.printResponse;

public class Post {
    // Set userAgent, URL and Post Parameters
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "https://httpbin.org/post";
    private static final String POST_PARAMS = "--NAME = Daycu";

    public static void sendPost(String HOST, int PORT) throws IOException {
        // Set URL
        URL obj = new URL(URL);
        // Set PROXY
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST, PORT));
        // Set CONNECTION
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

        // Requier POST method
        con.setRequestMethod("POST");
        // Requier userAgent
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response --> " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            printResponse(con);
        } else {
            System.out.println("POST request is not working");
        }
    }
}
