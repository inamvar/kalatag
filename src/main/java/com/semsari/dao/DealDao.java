package com.semsari.dao;

import java.util.Date;
import java.util.List;

import com.semsari.domain.*;


public interface DealDao  extends GenericDao<Item> {
	
	List<Item> findDealsByStatus(ItemStatus status);
	List<Item> findDealsByLabelAndStatus(DealLabel label, ItemStatus status);
	List<Item> findDealsByStatusAndNotLabel(DealLabel label, ItemStatus status);
	List<Item> findDealsByCategoryAndStatusAndNotLabel(ItemCategory category, DealLabel label, ItemStatus status);
	List<Item> findDealsByCustomer(Customer customer);
	List<Item> findSimilars(Item item);
    List<Item> find(int id, String name,DealLabel label,  SubCategory category,
                    ItemStatus status, String description, String terms,
                    String customerName ,String userName,String address,
                    City city, Date startDate, Date endDate,boolean notExpired,
                    String order,String asc, int pageSize, int pageNumber);
    int incrementVisit(Item item);

    int changeStatus(ItemStatus status, int itemId);





    List<Item> checkExpires();

	
}
