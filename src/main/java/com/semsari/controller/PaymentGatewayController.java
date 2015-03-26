package com.semsari.controller;

import com.semsari.domain.Item;
import com.semsari.domain.ItemStatus;
import com.semsari.gateway.PayLineGateway;
import com.semsari.listener.GenericMessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.semsari.domain.Transaction;
import com.semsari.domain.TransactiontStatus;
import com.semsari.gateway.PaymentGateway;
import com.semsari.service.CustomerService;

import com.semsari.service.DealService;
import com.semsari.service.ItemCategoryService;
import com.semsari.service.TransactionService;

import com.semsari.service.person.PersonService;

import javax.jms.Destination;

@Controller
@RequestMapping(value = "/payment/gateway")
public class PaymentGatewayController {

	private static final Logger logger = LoggerFactory
			.getLogger(PaymentGatewayController.class);


	@Autowired
	private DealService dealService;

	@Autowired
	private ItemCategoryService categoryService;



	@Autowired
	private CustomerService customerService;

	@Autowired
	private PersonService personService;


	@Autowired
	private PaymentGateway paymentGateway;

    @Autowired
    private TransactionService txnService;

    @Autowired
    JmsTemplate template;

    @Autowired
    Destination paymentNotification;


    @RequestMapping(value = "response/payline", method = RequestMethod.POST)
    public String payLineResult(@RequestParam("id_get") String id_get,
                              @RequestParam("trans_id") String trans_id) throws Exception {

            Transaction txn = txnService.findBy_id_get(id_get);
            if(txn !=null && txn.getStatus() == TransactiontStatus.PENDING){
                txn.setTrans_id(trans_id);
                String verify =  paymentGateway.verify(id_get,trans_id);
                int result = Integer.parseInt(verify);
                switch(result){
                    case -1 :

                        throw new Exception("Wrong API key for Payline");

                    case -2 :
                        throw new Exception("Invalid trans_id");

                    case -3 :
                        throw new Exception("Invalid id_get");

                    case -4:
                        throw new Exception("Transaction not found in payline");

                    case 1 :
                        txn.setStatus(TransactiontStatus.PAID);
                        Item item = txn.getItem();
                        if(item.getStatus() == ItemStatus.EXPIRED){
                         //   dealService.update(item);
                            dealService.changeStatus(ItemStatus.ON,item.getId());
                        }
                        else {
                            item.setStatus(ItemStatus.OFF);
                            dealService.update(item);

                        }
                        txnService.update(txn);
                        template.setDefaultDestination(paymentNotification);
                        MessageCreator messageCreator = new GenericMessageCreator<Transaction>(txn);
                        template.send(messageCreator);



                        break;
                    default:
                        txn.setStatus(TransactiontStatus.FAILED);
                        txnService.update(txn);
                        break;
                }
            }else{
                throw new Exception("Transaction not found or is not valid");
            }

        return "redirect:../../../customer/deal";

    }



}
