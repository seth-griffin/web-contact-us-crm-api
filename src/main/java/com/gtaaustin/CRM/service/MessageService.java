package com.gtaaustin.CRM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service("com.gtaustin.CRM.service.MessageService")
public class MessageService  {

    public static final String MESSAGE_FORMAT_TEXT = "text";
    public static final String MESSAGE_FORMAT_HTML = "html";

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public TemplateService templateService;

    public void sendEmailMessage(String to, String subject, String body, String messageFormat) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            helper.setFrom(mailFrom);
            helper.setTo(to);
            helper.setSubject(subject);
            mimeMessage = helper.getMimeMessage();


            if(messageFormat == MESSAGE_FORMAT_HTML)
                mimeMessage.setContent(body, "text/html; charset=utf-8");
            else
                mimeMessage.setText(body);

            emailSender.send(mimeMessage);
        }
        catch(Exception e) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailFrom);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            emailSender.send(message);
        }
    }

    public void sendEmailMessage(String to, String subject, String templateCodeKey, HashMap<String, String> templateArgs) {
        String templateText = templateService.getTemplateByCodeKey(templateCodeKey).getText();
        String body = templateService.processTemplate(templateArgs, templateText);
        sendEmailMessage(to, subject, body, MESSAGE_FORMAT_HTML);
    }
}
