package com.semsari.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.semsari.domain.*;
import com.semsari.gateway.PaymentGateway;
import com.semsari.listener.GenericMessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semsari.dao.DealDao;

import javax.jms.Destination;


@Service
public class DealServiceImp extends CRUDServiceImp<Item> implements DealService {

    @Autowired
    DealDao dealDao;


    @Autowired
    JmsTemplate template;

    @Autowired
    Destination itemAcceptedNotification;


    @Override
    @Transactional
    public Item setExpireDate(Item item) {
        try {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, item.getPeriod().getValue());
            item.setValidity(cal.getTime());
            item = update(item );
        }catch (Exception e){
            item.setValidity(new Date());
            e.printStackTrace();
        }
        return item;
    }

    @Override
    @Transactional
    public int incrementVisit(Item item) {
        return dealDao.incrementVisit(item);
    }

    @Override
    @Transactional
    public int changeStatus(ItemStatus status, int itemId) {

        if(status == ItemStatus.ON){
            Item item  =find(itemId);
           item = setExpireDate(item);

            try {
                template.setDefaultDestination(itemAcceptedNotification);
                MessageCreator messageCreator = new GenericMessageCreator<Item>(
                        item);
                template.send(messageCreator);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

        return dealDao.changeStatus(status, itemId);
    }

    @Override
    @Transactional
    public List<Item> find(int id, String name,DealLabel label, SubCategory category,
                           ItemStatus status, String description, String terms,
                           String customerName, String userName, String address,
                           City city, Date startDate, Date endDate, boolean notExpired,
                           String order,String asc, int pageSize, int pageNumber) {
        return dealDao.find(id, name,label, category, status, description,
                terms,customerName, userName, address,  city, startDate, endDate,
                notExpired,order, asc, pageSize, pageNumber);
    }


    @Override
	@Transactional
	public List<Item> findDealsByStatus(ItemStatus status) {
		return dealDao.findDealsByStatus(status);
	}

	@Override
	@Transactional
	public List<Item> findDealsByLabelAndStatus(DealLabel label,
			ItemStatus status) {
		return dealDao.findDealsByLabelAndStatus(label, status);
	}

	@Override
	@Transactional
	public List<Item> findDealsByStatusAndNotLabel(DealLabel label,
			ItemStatus status) {
		return dealDao.findDealsByStatusAndNotLabel(label, status);
	}

	@Override
	@Transactional
	public List<Item> findDealsByCategoryAndStatusAndNotLabel(
			ItemCategory category, DealLabel label, ItemStatus status) {
		return dealDao.findDealsByCategoryAndStatusAndNotLabel(category, label,
				status);
	}

	@Override
	@Transactional
	public List<Item> findDealsByCustomer(Customer customer) {
		return dealDao.findDealsByCustomer(customer);
	}

	@Override
	@Transactional
	public List<Item> findSimilars(Item item) {
		return dealDao.findSimilars(item);
	}

	@Override
	public boolean isExpired(Item item) {
		if (item.getValidity().compareTo(new Date()) < 0){
			return true;
		}else{
			return false;
		}
	}


    @Override
    @Transactional
    public  List<Item> checkExpires(){
        return dealDao.checkExpires();
    }
}
