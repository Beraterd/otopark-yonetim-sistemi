package com.example.otoparkprojesi.controller;

import com.example.otoparkprojesi.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMailController {

    private final EmailService emailService;

    public TestMailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/api/test-mail")
    public String sendTestMail(@RequestParam String to) {
        String subject = "Otopark Test Maili";
        String content = "Bu bir test mailidir. Mail servisi başarıyla çalışıyor!";
        emailService.sendEmail(to, subject, content);
        return "Mail gönderildi -> " + to;
    }
}
