package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Thread extends java.lang.Thread {
    // Set Variable for socket and images names
    private Socket socket;
    private String name;

    Thread(Socket socket, String name) {
        // Call those variables for usability
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        // set path for saving photos
        File file = new File("D:\\Univer\\Anul III\\sem2\\PR_Java\\lab1\\src\\images\\" + name);
        System.out.println(file.getPath());
        // connect to website
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = socket.getInputStream();

            // Header
            boolean headerEnded = false;

            byte[] bytes = new byte[2048];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                // If the header has been reached it will write bytes as usual
                if (headerEnded) {
                    fileOutputStream.write(bytes, 0, length);
                } else {
                    for (int i = 0; i < 2048; i++) {
                        if (bytes[i] == 13 && bytes[i + 1] == 10 && bytes[i + 2] == 13 && bytes[i + 3] == 10) {
                            headerEnded = true;
                            fileOutputStream.write(bytes, i + 4, 2048 - i - 4);
                            break;
                        }
                    }
                }
            }
            // closing stream
            inputStream.close();
            fileOutputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

