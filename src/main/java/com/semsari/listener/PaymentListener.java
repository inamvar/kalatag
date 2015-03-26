package com.semsari.listener;

import com.semsari.domain.Item;
import com.semsari.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Component("paymentListener")
public class PaymentListener {

	private Logger log = LoggerFactory.getLogger(PaymentListener.class);
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;
	
	
	public void onMessage(Transaction txn) {

		try {
			
			sendEmail(txn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(Transaction txn) {

		try {
			Locale locale = LocaleContextHolder.getLocale();
			log.debug("locale from context=" + locale);
			// locale = new Locale("es_ES");
			// locale = new Locale("ar_AE");
			locale = new Locale("fa");
			log.debug("locale=" + locale);

			String[] params = new String[8];
			params[0] = txn.getPerson().getFirstName();
			params[1] = txn.getPerson().getLastName();
            params[2] = txn.getItem().getName();
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm:ss");
			params[3] = dt1.format(txn.getDate());
			params[4] = dt2.format(txn.getDate());
            params[5] = txn.getAmount() +"";
            params[6] = txn.getTrans_id();
            params[7] = txn.getId_get();

                    String htmlText = messageSource.getMessage("email.payment", params, locale);
			System.setProperty("mail.mime.charset", "UTF-8");
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();

			// add html part
			messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(multipart);
			helper.setTo(txn.getPerson().getUsername());
			helper.setSubject(messageSource.getMessage("email.payment.subject", null, locale));
			mailSender.send(mimeMessage);
			log.info("For payment notification an email to "
					+ txn.getPerson().getUsername() + " has been sent.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
