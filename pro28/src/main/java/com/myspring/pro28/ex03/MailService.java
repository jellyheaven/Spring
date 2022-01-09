package com.myspring.pro28.ex03;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//@Service("mailService")
public class MailService {
	
	//@Autowired
	private JavaMailSender mailSender;
	
	//@Autowired
	private SimpleMailMessage preConfiguredMessage;
	
	//@Async
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "UTF-8");
			messageHelper.setCc("mayatoto@naver.com");
			messageHelper.setFrom("donking1004@gmail.com", "박재현");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to); 
			messageHelper.setText(body );
			mailSender.send(message);  
			
		} catch (MessagingException | UnsupportedEncodingException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Async
	public void sendPreConfiguredMail(String message) {
	  SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
	  mailMessage.setText(message);
	  mailSender.send(mailMessage);
	}
	
	
}
