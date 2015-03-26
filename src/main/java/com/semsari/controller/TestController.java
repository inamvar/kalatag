package com.semsari.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.semsari.domain.Gender;

import com.semsari.domain.Person;

import com.semsari.service.person.PersonService;
import com.semsari.util.Util;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	public PersonService getService() {
		return service;
	}

	public void setService(PersonService service) {
		this.service = service;
	}

	@Autowired
	PersonService service;


	@RequestMapping(value = "/payment/simulator", method = { RequestMethod.POST, RequestMethod.GET })
	public String paymentGatewaySimolator(@RequestParam("Amount") int amount,
			@RequestParam("MID") String merchantId,
			@RequestParam("ResNum") String reservationNumber,
			@RequestParam("RedirectURL") String redirectUrl, Model uiModel) {
		
		
		String referenceNumber = UUID.randomUUID().toString().substring(0, 6)
				.toUpperCase();
		Random rand = new Random();
		int traceNo = rand.nextInt(999999) + 1000;
		/*try {

			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TraceNo",traceNo);
			params.put("MID", merchantId);
			params.put("ResNum", reservationNumber);
			params.put("ResNum", referenceNumber);
			params.put("State", "OK");
			String urlParameters = "?State={State}&RefNum={RefNum}&ResNum={ResNum}&MID={MID}&TraceNo={TraceNo}";			
			restTemplate.postForLocation(redirectUrl + urlParameters , String.class, params);
		} catch (Exception e) {
			e.getStackTrace();
		}*/
		
		uiModel.addAttribute("TraceNo" , traceNo);
		uiModel.addAttribute("MID" , merchantId);
		uiModel.addAttribute("ResNum", reservationNumber);
		uiModel.addAttribute("RefNum",referenceNumber);
		uiModel.addAttribute("Amount",amount);
		uiModel.addAttribute("RedirectURL",redirectUrl);
		System.out.println("request delivered to gateway. \n redirectUrl="+ redirectUrl);
		return "bank-simulation";
	}

	@RequestMapping(value = "/pass", method = RequestMethod.GET)
	public String generatePassword(Model model) {
		model.addAttribute("msg", Util.generateRandomPassword());
		return "test";
	}


    @RequestMapping(value = "/addcustomer", method = { RequestMethod.POST, RequestMethod.GET })
	public String addCustomer(Model model) {
		try {
			Person p = new Person();
			p.setFirstName("Iman");
			p.setLastName("Namvar");
			p.setGender(Gender.Female);

			p.setUsername("iman.namvar@gmail.com");
			service.create(p);
			model.addAttribute("msg", p.toString());
			return "test";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
