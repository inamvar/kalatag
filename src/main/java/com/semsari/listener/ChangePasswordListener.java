package com.semsari.listener;

import java.util.Locale;

import javax.mail.BodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.semsari.domain.Person;


@Component("changePasswordListener")
public class ChangePasswordListener {

	private Logger log = LoggerFactory.getLogger(ChangePasswordListener.class);
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;
	
	
	public void onMessage(Person person) {

		try {
			
			sendEmail(person);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(Person person) {

		try {
			Locale locale = LocaleContextHolder.getLocale();
			log.debug("locale from context=" + locale);
			// locale = new Locale("es_ES");
			// locale = new Locale("ar_AE");
			locale = new Locale("fa");
			log.debug("locale=" + locale);

			String[] params = new String[4];
			params[0] = person.getFirstName();
			params[1] = person.getLastName();
			params[2] = person.getUsername();
			params[3] = person.getPassword();

			String htmlText = messageSource.getMessage("email.user.password.change", params, locale);
			System.setProperty("mail.mime.charset", "UTF-8");
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();

			// add html part
			messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(multipart);

			helper.setTo(person.getUsername());
			helper.setSubject(messageSource.getMessage("email.user.password.change.subject", null, locale));
            InternetAddress from = new InternetAddress( ((JavaMailSenderImpl)mailSender).getUsername(), "Kalatag" );
            helper.setFrom(from);
			mailSender.send(mimeMessage);
			log.info("For change password notification an email to {0} has been sent.", person.getUsername());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
