package com.alfauz.orderme.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailService {

//    private final JavaMailSender javaMailSender;
//
//    public void sendEmail(final String subject, final String text, final String... to) {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(to);
//
//        msg.setSubject(subject);
//        msg.setText(text);
//
//        javaMailSender.send(msg);
//    }
//
//    void sendEmailWithAttachment(final String subject, final String text, final String... to) throws MessagingException, IOException {
//
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        // true = multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//
//        helper.setTo(to);
//
//        helper.setSubject(subject);
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
//        helper.setText("<h1>" + text + "</h1>", true);
//
//        // hard coded a file path
//        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
//
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
//
//        javaMailSender.send(msg);
//
//    }
}
