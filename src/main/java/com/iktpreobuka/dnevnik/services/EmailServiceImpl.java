package com.iktpreobuka.dnevnik.services;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.iktpreobuka.dnevnik.entities.EmailObject;

public class EmailServiceImpl implements EmailService {

	//@Autowired
	public JavaMailSenderImpl emailSender = new JavaMailSenderImpl();

	@Override
	public void sendSimpleMessage(EmailObject object) {
		
		emailSender.setPort(587);
		emailSender.setHost("smtp.gmail.com");
		emailSender.setUsername("knezevicd213@gmail.com");
		emailSender.setPassword("Matematicarka1");
		Properties props = emailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(object.getTo());
		message.setSubject(object.getSubject());
		message.setText(object.getText());
		
		emailSender.send(message);
	}

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {
		
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		
		helper.setTo(object.toString());
		helper.setSubject(object.toString());
		
		String text = "<html><body><table" 
					+ "style='border:2px solid black'>"
					+ "<tr><td>" + object.getText()
					+ "</td></tr>" + "</table></body></html>";
		helper.setText(text, true);
		
		emailSender.send(mail);
	}

	@Override
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception {
		
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		helper.setText(object.getText(), false);
		
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(file.getFilename(), file);
		
		emailSender.send(mail);
	}

}

