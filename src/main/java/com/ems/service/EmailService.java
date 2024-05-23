package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String from,String to,String text)  {
		try {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(message);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setText(text);
		System.out.println(text);
		javaMailSender.send(message);
		}
		catch(Exception e) {}
	}

}
