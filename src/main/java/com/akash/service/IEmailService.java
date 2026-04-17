package com.akash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class IEmailService {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String subject, String body) {

		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			
			mailSender.send(mimeMessage);
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
