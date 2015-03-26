package com.semsari.aspect;

import javax.jms.Destination;


import com.semsari.domain.Item;
import com.semsari.domain.Transaction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.semsari.domain.Customer;

import com.semsari.domain.Person;

import com.semsari.listener.GenericMessageCreator;
import com.semsari.listener.RegistrationListener;
import com.semsari.service.person.PersonService;
import com.semsari.util.Util;

@Aspect
public class NotificationAspect {

	@Autowired
	JmsTemplate template;

	@Autowired
	Destination emailNotification;

	@Autowired
	Destination changePasswordNotification;

	@Autowired
	Destination resetPasswordNotification;


    @Autowired
    Destination itemCreatedNotification;



	@Autowired
	PersonService personService;


	private Logger log = LoggerFactory.getLogger(NotificationAspect.class);



	@Around("within(com.semsari.service.CRUDService+) && target(com.semsari.service.CustomerServiceImp) && execution(* create(..))")
	public Customer aroundCustomerCreate(ProceedingJoinPoint pjp)
			throws Throwable {

		Object[] args = pjp.getArgs();
		Customer customer = (Customer) args[0];
	//	String password = Util.generateRandomPassword();
        String password = customer.getPassword();
		String encryptedPassword = Util.toSHA256(password);
		customer.setPassword(encryptedPassword);

			customer = (Customer) pjp.proceed();
			customer.setPassword(password);
        try {
			template.setDefaultDestination(emailNotification);
			MessageCreator messageCreator = new GenericMessageCreator<Customer>(
					customer);
			template.send(messageCreator);
        } catch (Exception e) {
            e.printStackTrace();

        }
			customer.setPassword(encryptedPassword);
			log.info(String.format("New customer registerd: id={0}, username={1}",customer.getId(),customer.getUsername()));
			return customer;


	}
	


	@Around("within(com.semsari.service.CRUDService+) && target(com.semsari.service.person.PersonServiceImp) && execution(* changePassword(..))")
	public int afterPerosnChangePassword(ProceedingJoinPoint pjp) {
		int ret = 0;
		Object[] args = pjp.getArgs();
		int id = (Integer) args[0];
		Person person = personService.find(id);
		if (person != null) {
			String password = (String) args[1];
			String encryptedPassword = Util.toSHA256(password);
			

			try {
				ret = (Integer) pjp.proceed();
				person.setPassword(password);
				template.setDefaultDestination(changePasswordNotification);

				MessageCreator messageCreator = new GenericMessageCreator<Person>(
						person);
				template.send(messageCreator);
				person.setPassword(encryptedPassword);
				return ret;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			} catch (Throwable e) {
				e.printStackTrace();
				return -2;
			}
		} else
			return -3;
	}

	@Around("within(com.semsari.service.CRUDService+) && target(com.semsari.service.person.PersonServiceImp) && execution(* resetPassword(..))")
	public int afterPerosnResetPassword(ProceedingJoinPoint pjp) {
		int ret = 0;
		Object[] args = pjp.getArgs();
		int id = (Integer) args[0];
		Person person = personService.find(id);
		if (person != null) {
			String password = Util.generateRandomPassword();
			 String encryptedPassword = Util.toSHA256(password);
		

			try {
				ret = (Integer) pjp.proceed(new Object[] { id, password });
				person.setPassword(password);
				template.setDefaultDestination(resetPasswordNotification);

				MessageCreator messageCreator = new GenericMessageCreator<Person>(
						person);
				template.send(messageCreator);
				person.setPassword(encryptedPassword);
				return ret;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			} catch (Throwable e) {
				e.printStackTrace();
				return -2;
			}
		} else
			return -3;
	}



    @AfterReturning(pointcut = "within(com.semsari.service.CRUDService+) && target(com.semsari.service.DealServiceImp) && execution(* create(..))", returning = "item")
    public void afterItemCreate(JoinPoint jp, Item item) throws Throwable {
        log.debug("after create item id=" + item.getId()
                + ", sending item to qserver...");

  /*      item.setThumbnail(null);
        item.setImage1(null);
        item.setImage2(null);
        item.setImage3(null);
        item.setImage4(null);*/
        template.setDefaultDestination(itemCreatedNotification);
        MessageCreator messageCreator = new GenericMessageCreator<Item>(item);
        template.send(messageCreator);


    }




}
