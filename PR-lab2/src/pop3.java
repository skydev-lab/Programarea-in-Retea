import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;

public class pop3 {
    private static final String HOST = "pop.gmail.com";
    private static final String USERNAME = "dan.chiriac1453@gmail.com";
    private static final String PASSWORD = "****";

    public static void doit() throws MessagingException, IOException {
        // Folder for mail messages
        Folder folder = null;
        // Models the messages and its access protocol,
        // for storing and retrieving messages.
        Store store = null;

        try {
            // List of value
            Properties props = new Properties();
            props.put("mail.store.protocol", "pop3s");
            // Represents a mail session collecting together
            // props and defaults used by the mail API's.
            Session session = Session.getDefaultInstance(props);
            // get an instance of the store specified by Provider.
            store = session.getStore();
            // connect to user account.
            store.connect(HOST, USERNAME, PASSWORD);
            // getDefaultFolder() => returns a folder obj that respresents
            // the 'root' of the default namespace presented to the user by the Store.
            folder = store.getDefaultFolder().getFolder("INBOX");
            // Read only for messages
            folder.open(Folder.READ_ONLY);
            // get the messages with array
            Message[] messages = folder.getMessages();
            // get messages count
            System.out.println("No of Message: " + folder.getMessageCount());
            // get unread messages count
            System.out.println("No of Unread Messages: " + folder.getUnreadMessageCount());

            for (int i = 0; i < messages.length; ++i) {
                System.out.println("MESSAGE #" + (i + 1) + ":");
                Message msg = messages[i];
                String from = "unknown";
                if (msg.getReplyTo().length >= 1) {
                    from = msg.getFrom()[0].toString();
                } else if (msg.getFrom().length >= 1) {
                    from = msg.getFrom()[0].toString();
                }

                String subject = msg.getSubject();
                System.out.println("Saving ... " + subject + " " + from);

                String filename = "d:/Univer/" + subject;
                //saveParts(msg.getContent(), filename);
            }
        } finally {
            if (folder != null) {
                folder.close(true);
            }
            if ( store != null) {
                store.close();
            }


        }

    }

    public static void saveParts(Object content, String filename) throws IOException, MessagingException {

        OutputStream out = null;
        InputStream in = null;
        try {
            if (content instanceof Multipart) {
                Multipart multi  = ((Multipart)content);
                int parts = multi.getCount();

                for (int j = 0; j < parts; ++j) {
                    MimeBodyPart part = (MimeBodyPart)multi.getBodyPart(j);
                    if (part.getContent() instanceof Multipart) {
                        saveParts(part.getContent(), filename);
                    } else {
                        String extension = "";
                        if (part.isMimeType("text/html")) {
                            extension = "html";
                        } else {
                            if (part.isMimeType("text/plain")) {
                                extension = "txt";
                            } else {
                                extension = part.getDataHandler().getName();
                            }
                            filename = filename + "." + extension;
                            System.out.println("... " + filename);
                            out = new FileOutputStream(new File(filename));
                            in = part.getInputStream();
                            int k;
                            while ((k = in.read()) != -1) {
                                out.write(k);
                            }
                        }
                    }
                }
            }

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.flush(); out.close();
            }
        }
    }



    public static void main(String[] args) throws Exception {
        MailReceiver.doit();


    }
}
