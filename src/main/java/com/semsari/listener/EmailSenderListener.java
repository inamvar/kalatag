package com.semsari.listener;

import java.util.Locale;


import javax.jms.Destination;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.semsari.domain.Customer;

import com.semsari.domain.Person;


@Component("emailSenderListener")
public class EmailSenderListener {

	private Logger log = LoggerFactory.getLogger(EmailSenderListener.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	JmsTemplate template;

	@Autowired
	private MessageSource messageSource;




	Locale locale = new Locale("fa");

	public void onMessage(Customer customer) {

		try {
			log.info("Registeration customer: " + customer
					+ " registered successfully.");
			sendCustomerRegisterEmail(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void sendCustomerRegisterEmail(Person person) {

		try {
			Locale locale = LocaleContextHolder.getLocale();

			String[] params = new String[4];
			params[0] = person.getFirstName();
			params[1] = person.getLastName();
			params[2] = person.getUsername();
			params[3] = person.getPassword();

			String htmlText = messageSource.getMessage(
					"email.user.registration", params, locale);

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					false, "utf-8");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();

			// add html part
			messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(multipart);
			helper.setTo(person.getUsername());
			helper.setSubject(messageSource.getMessage(
					"email.user.registration.subject", null, locale));
			mailSender.send(mimeMessage);
			log.info("For registeration notification an email to "
					+ person.getUsername() + " has been sent.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}