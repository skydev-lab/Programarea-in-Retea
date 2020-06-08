package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class UTMClient {
    // Set variable for socket, listing images, url address and port
    private SSLSocket socket;
    private List<String> imageList = new ArrayList<>();
    private InetAddress address;
    private int port;

    // conecting to client
    public UTMClient(InetAddress address, int port) throws IOException {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        socket = (SSLSocket) factory.createSocket(address, port);
        socket.startHandshake();
        this.address = address;
        this.port = port;
    }

    public void getRequest() throws IOException {
        //Instantiates a new PrintWriter passing in the sockets output stream
        PrintWriter Pout = new PrintWriter(socket.getOutputStream(), true);
        //Creates a BufferedReader that contains the server response
        BufferedReader Bin = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        Pout.println("GET / HTTP/1.1");
        Pout.println("Host: " + socket.getInetAddress().getHostName());
        Pout.println("Cache-Control: max-age=3, must-revalidate");
        Pout.println("Content-Type: text/html; charset=UTF-8");
        Pout.println("");
        Pout.flush();

        String line;
        String pattern = "[^\\/]+(.jpg|.gif|.png)+";
        while ((line = Bin.readLine()) != null) {
            Matcher matcher = Pattern.compile(pattern).matcher(line);
            if (matcher.find()) {
                imageList.add(matcher.group());
            }
            System.out.println(line);
        }
        Bin.close();
        Pout.close();
        socket.close();
    }

    public void getImages() throws IOException {
        for (String image : imageList) {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = (SSLSocket) factory.createSocket(address, port);

            PrintWriter Pout = new PrintWriter(socket.getOutputStream(), true);

            Pout.println("GET " + image + " HTTP/1.1");
            Pout.println("Host: " + socket.getInetAddress().getHostName());
            Pout.println("Cache-Control: max-age=3, must-revalidate");
            Pout.println("Content-Type: text/html; charset=UTF-8");
            Pout.println("");

            Thread thread = new Thread(socket, image);
            thread.start();
        }
    }
}
