package com.pancakes.notification;

import com.pancakes.proxy.NotificationProxy;
import jakarta.mail.*;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class Email implements NotificationProxy {

    @Value("{email.username}")
    private String username;

    @Value("{email.pass}")
    private String pass;

    @Value("{email.recipient}")
    private String recipient;

    @Override
    public void send(StringBuilder msg) {

        if (msg == null || msg.length() < 1)
            return;

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        jakarta.mail.Session session = jakarta.mail.Session.getInstance(prop, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Pancakes");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg.toString(), "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email SENT");

        } catch (Exception e) {
            System.out.println("Email FAILED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
