package main;


import main.requests.Get;
import main.requests.Head;
import main.requests.Options;
import main.requests.Post;

import java.io.IOException;

public class Client {

    private static String HOST = "77.238.79.111";
    private static int PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        // GET request
        System.out.println("GET:");
        Get.sendGet(HOST, PORT);
        System.out.println(System.lineSeparator());

        // POST request
        System.out.println("POST:");
        Post.sendPost(HOST, PORT);
        System.out.println(System.lineSeparator());

        // OPTION request
        System.out.println("OPTIONS:");
        Options.sendOptions(HOST, PORT);
        System.out.println(System.lineSeparator());

        // HEAD request
        System.out.println("HEAD:");
        Head.sendHead(HOST, PORT);





    }
}
