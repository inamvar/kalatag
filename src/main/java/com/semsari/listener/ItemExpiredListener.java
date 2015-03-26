package com.semsari.listener;

import com.semsari.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Locale;


@Component("itemExpiredListener")
public class ItemExpiredListener {

	private Logger log = LoggerFactory.getLogger(ItemExpiredListener.class);
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;
	
	
	public void onMessage(Item item) {

		try {
			
			sendEmail(item);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(Item item) {

		try {
			Locale locale = LocaleContextHolder.getLocale();
			log.debug("locale from context=" + locale);

			locale = new Locale("fa");
			log.debug("locale=" + locale);

    		String[] params = new String[3];
			params[0] = item.getCustomer().getFirstName();
			params[1] = item.getCustomer().getLastName();
			params[2] = item.getName();

			String htmlText = messageSource.getMessage("email.item.expired", params, locale);
			System.setProperty("mail.mime.charset", "UTF-8");
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();

			// add html part
			messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(multipart);
			helper.setTo(item.getCustomer().getUsername());
			helper.setSubject(messageSource.getMessage("email.item.expired.subject", null, locale));
            helper.setFrom(((JavaMailSenderImpl)mailSender).getUsername());
			mailSender.send(mimeMessage);
			log.info("For Item expired notification an email to "
					+ item.getCustomer().getUsername() + " has been sent.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
