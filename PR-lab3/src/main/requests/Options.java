package main.requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class Options {

    // Set URL
    private static final String URL = "https://httpbin.org/post";

    public static void sendOptions(String HOST, int PORT) throws IOException {
        // Call URL
        URL obj = new URL(URL);
        // set PROXY
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST, PORT));
        //set CONNECTION
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

        // Require OPTIONS method
        con.setRequestMethod("OPTIONS");
        System.out.println(con.getHeaderField("Allow"));
    }
}
