package com.semsari.schedule;

import com.semsari.domain.Item;
import com.semsari.listener.GenericMessageCreator;
import com.semsari.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import java.util.List;

/**
 * Created by Iman on 3/23/2015.
 */
public class CheckExpiresTask {

    private static final Logger logger = LoggerFactory
            .getLogger(CheckExpiresTask.class);

    @Autowired
    DealService dealService;
    @Autowired
    JmsTemplate template;



    @Autowired
    Destination itemExpiredNotification;

    public void check(){

        logger.info("Checking for expired items...");
        List<Item> items = dealService.checkExpires();
        int idx = 1;
        for(Item item: items){
            template.setDefaultDestination(itemExpiredNotification);
            MessageCreator messageCreator = new GenericMessageCreator<Item>(item);
            template.send(messageCreator);
            try {
                Thread.sleep(1000 * 15 );
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        logger.info("{0} items expired",items.size());
    }


}
