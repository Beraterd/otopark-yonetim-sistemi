package com.example.otoparkprojesi.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String sender;
    @org.springframework.beans.factory.annotation.Value("${spring.mail.password:}")
    private String mailPassword;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        if (mailPassword.isBlank()) return; // Local demo: SMTP is optional.
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
