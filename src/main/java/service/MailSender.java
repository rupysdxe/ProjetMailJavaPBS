package service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MailSender {
    private final String senderGmailId="**";
    private final String senderGmailPassword="**";
    private Session session;
    private MimeMessage mimeMessage;

    public MailSender(){
        session();
    }
    private void session(){
        Properties properties=new Properties();
        ///host set
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderGmailId,senderGmailPassword);
            }
        });
        session.setDebug(true);
        this.session=session;
    }
    public void sendMultiple(String subject, String message, ArrayList<String> emailList){
        try {
            MimeMessage mimeMessage=new MimeMessage(session);
            for(String tempEmail:emailList){
                mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(tempEmail));
            }
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(message,"text/html");
            this.mimeMessage=mimeMessage;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public void setSubjectAndMessage(String subject, String message,String receiverGmailId){
        try {
            MimeMessage mimeMessage=new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverGmailId));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent("<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\n" +
                    "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\n" +
                    "    <div style=\"border-bottom:1px solid #eee\">\n" +
                    "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">OPT Test</a>\n" +
                    "    </div>\n" +
                    "    <p style=\"font-size:1.1em\">Hi,</p>\n" +
                    "    <p>Use the following OTP. OTP is valid for 10 minutes</p>\n" +
                    "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"+message+"</h2>\n" +
                    "    <p style=\"font-size:0.9em;\">Regards,<br />Team Windbreaker</p>\n" +
                    "    <hr style=\"border:none;border-top:1px solid #eee\" />\n" +
                    "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\n" +
                    "      <p>Presidential Business School Java Project</p>\n" +
                    "      <p>Kathmandu</p>\n" +
                    "      <p>Nepal</p>\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</div>","text/html");
            this.mimeMessage=mimeMessage;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public void setMessageAndAttachment(String subject, String message, File file,String receiverGmailId){
        try {
            MimeMessage mimeMessage=new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverGmailId));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            ///Attaching the file
            MimeMultipart attachment=new MimeMultipart();
            MimeBodyPart mimeBodyPart=new MimeBodyPart();
            try {
                mimeBodyPart.attachFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            attachment.addBodyPart(mimeBodyPart);

            mimeMessage.setContent(attachment);
            ///Send the message
            this.mimeMessage=mimeMessage;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public void send(){
        try {
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}