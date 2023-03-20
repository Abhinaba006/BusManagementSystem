package nrifintech.busMangementSystem.Service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCredentials(String recipient, String username, String password) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("nribus01@gmail.com");
        helper.setTo(recipient);
        helper.setSubject("New User Credentials");
        helper.setText("Hello,\n\n"
                + "Your account for the NRI bus has been created with the following credentials:\n"
                + "Email: " + recipient + "\n"
                + "Password: " + password + "\n\n"
                + "Please keep these credentials safe and do not share them with anyone.\n\n"
                + "Best regards,\n"
                + "NRI Bus Service");

        mailSender.send(message);
    }
}

