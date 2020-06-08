package main;

import java.io.IOException;
import java.net.InetAddress;

public class mainClient {
    public static void main(String[] args) throws IOException {

        InetAddress addressUtm = InetAddress.getByName("utm.md");
        UTMClient utm = new UTMClient(addressUtm, 443);
        utm.getRequest();
        utm.getImages();
    }
}
