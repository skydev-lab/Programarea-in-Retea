import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class smtp {
    public static void main(String[] args)
    {
        final String USERNAME = "dan.chiriac1453@gmail.com";
        final String PASSWORD = "****";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dan.chiriac1453@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("dan.chiriac1453@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Set me as the crowled fire of arms");
            Transport.send(message);
            System.out.println("DONE");
        } catch (MessagingException e) {
            e.printStackTrace();

        }

    }
}
