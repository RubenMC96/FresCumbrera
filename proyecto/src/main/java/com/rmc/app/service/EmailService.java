package com.rmc.app.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.lang.String;

@Service
public class EmailService {
    // @Autowired
    // private JavaMailSender sender;

    public boolean sendEmail(String destination, String subject, String textMessage) throws RuntimeException {
        // try {
        // MimeMessage message = sender.createMimeMessage();
        // MimeMessageHelper helper = new MimeMessageHelper(message);

        // helper.setTo(destination);
        // helper.setText(textMessage, true);
        // helper.setSubject(subject);
        // sender.send(message);
        // System.out.println(message);
        // return true;
        // } catch (MessagingException e) { e.printStackTrace(); return false; }


        try {


            String host = "localhost";
            Properties props = System.getProperties();

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", 25);
            props.put("mail.smtp.auth", "false");
            props.put("mail.smtp.starttls.enable", "false");

            String[] destinatarios = new String[1]; // Crear un array con capacidad para 5 elementos
            destinatarios[0] = destination;
  
            String[] to = destinatarios;
            Session session = Session.getDefaultInstance(props, null);

            MimeMessage message = new MimeMessage(session);

            InternetAddress[] toAddress = new InternetAddress[to.length];

            for(int i = 0; i < to.length; i++){
                toAddress[i] = new InternetAddress(to[i]);
            }
            for(int i = 0; i < toAddress.length; i++){
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            
            message.setSubject(subject);
            message.setText(textMessage);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, null, null);
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();

            return true;

        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el email de contacto", e);
        }
    }
    
}