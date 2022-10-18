package org.example;

import service.MailSender;

import java.time.LocalDateTime;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MailSender mailSender= new MailSender();
        int rand = (int)(Math.random() * (9999));
        String id = String.format("%04d",rand );
        mailSender.setSubjectAndMessage("OTP",id,"upretisaurav7@gmail.com");
        mailSender.send();

    }
}