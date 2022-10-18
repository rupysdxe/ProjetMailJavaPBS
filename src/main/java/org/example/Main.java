package org.example;
import service.DatabaseConnector;
import service.MailSender;
import service.OTPChecker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        otpDemo();
    }
    public static void sendEmail(){
        MailSender mailSender = new MailSender();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<String> emailList = databaseConnector.getAllEmail();
        mailSender.sendMultiple("Test", "random", emailList);
    }
    public static void otpDemo(){
        Scanner scanner= new Scanner(System.in);
        MailSender mailSender = new MailSender();
        int rn = (int) (Math.random() * 99999);
        String realOTP = String.format("%05d",rn);
        System.out.println("Enter Your Email Address");
        String inEmail = scanner.next();
        mailSender.setSubjectAndMessage("OTP Test",realOTP,inEmail);
        mailSender.send();
        System.out.println("Enter the OPT in your Email Address");
        String inputOTP = scanner.next();
        if(OTPChecker.checker(inputOTP,realOTP)){
            System.out.println("Success");
        }else{
            System.out.println("Failure");
        }


    }
}