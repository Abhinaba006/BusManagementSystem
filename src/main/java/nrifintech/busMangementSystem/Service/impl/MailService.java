package nrifintech.busMangementSystem.Service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.entities.User;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCredentials(String recipient, String employee_name, String password,String action) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
//        helper.setText("<html><body><h1>Welcome to NRI Bus Service</h1><p>Here is the logo:</p><img src='cid:nrif_logo'></body></html>", true);
//		FileSystemResource file = new FileSystemResource("client/public/add.png");
//		helper.addInline("nrif_logo", file);

        helper.setFrom("nribus01@gmail.com");
        helper.setTo(recipient);
        helper.setSubject("User Credentials");
        helper.setText("<html><body><p>Dear <strong>"+employee_name+"</strong>,</p>"
        		+ "<p>I am pleased to inform you that we have "+action+" an account for you on our bus service platform. Please find below your login credentials:</p>"
        				+ "<p><strong>Username:</strong> "+recipient+"</p><p><strong>Password:</strong> "+password+"</p>"
        						+ "<p>With this account, you will be able to access our bus service and make use of its features.</p>"
        						+ "<p>Please ensure that you receive this information and are able to log in to the platform without any issues.</p>"
        						+ "<p>If you have any questions or concerns, please do not hesitate to reach out to me.</p><br/><p>Thank you,</p>"
        						+ "<p><strong>NRI Bus Service</strong></p>"
        						+ "<img src=\"http://www.nrifintech.com/img/NRIFintech-logo.png\" alt=\"Example Image\" style=\"display:block; width:100%;height:200px; max-width:600px;\"/>\r\n"
        						+ ""
        						+ "</body></html>", true);

        mailSender.send(message);
    }
    
}
