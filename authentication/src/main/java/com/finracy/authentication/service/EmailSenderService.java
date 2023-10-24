package com.finracy.authentication.service;

import com.finracy.authentication.dto.exception.CanNotSendEmailException;
import com.finracy.authentication.dto.exception.ErrorCode;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        /*try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setFrom(new InternetAddress(from, "finracy.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

        } catch (MessagingException e) {
            throw CanNotSendEmailException.builder()
                    .msg(e.getMessage() )
                    .errorCode(ErrorCode.CAN_NOT_SEND_MESSAGE)
                    .objectCausedException(e)
                    .instant(Instant.now())
                    .build();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }*/
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }
}
