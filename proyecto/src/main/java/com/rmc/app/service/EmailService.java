package com.rmc.app.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


    @Service
    public class EmailService {
        @Autowired
        private JavaMailSender sender;
    
        public boolean sendEmail(String to, String from, String subject, String textMessage) {
            try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
    
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            return true;
            } catch (MessagingException e) { e.printStackTrace(); return false; }
        }
        
    }
