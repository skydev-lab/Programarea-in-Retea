package main.requests;

import java.io.*;
import java.net.*;

import static main.utlis.printResponse;

public class Get {
    // Set userAgent and url
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "http://webcode.me";

    public static void sendGet(String HOST, int PORT) throws IOException {
        // call URL
        URL obj = new URL(URL);
        // set PROXY
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST, PORT));
        // set CONNECTION
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

        // Require GET method
        con.setRequestMethod("GET");
        // Set userAgent
        con.setRequestProperty("User-Agent", USER_AGENT);

        // get resonse
        int responseCode = con.getResponseCode();

        System.out.println("GET Response --> " + responseCode);

        // error watcher
        if (responseCode == HttpURLConnection.HTTP_OK) {
            printResponse(con);
        } else {
            System.out.println("GET request is not working");
        }
    }
}
