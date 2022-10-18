package org.example;

import service.MailSender;

import java.time.LocalDateTime;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MailSender mailSender= new MailSender();
        Random random = new Random(2022);
        String id = String.format("%04d", random.nextInt(10000));
        mailSender.setSubjectAndMessage("OTP",id,"upretisaurav7@gmail.com");
        mailSender.send();

    }
}