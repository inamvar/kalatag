package com.semsari.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.semsari.domain.*;
import com.semsari.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DealDaoImp extends GenericDaoImp<Item> implements DealDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Item find(int id) {

		Item item = (Item) this.sessionFactory.getCurrentSession().get(
				Item.class, id);


		return item;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findDealsByStatus(ItemStatus status) {
		List<Item> results = new ArrayList<Item>();
		String hql = "FROM  Item D WHERE D.status = :status order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		results = query.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findDealsByLabelAndStatus(DealLabel label,
			ItemStatus status) {
		List<Item> results = new ArrayList<Item>();
		String hql = "FROM Item D WHERE D.status = :status AND D.label= :label order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
		results = query.list();


		return results;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findDealsByStatusAndNotLabel(DealLabel label,
			ItemStatus status) {
		List<Item> results = new ArrayList<Item>();
		String hql = "FROM Item D WHERE D.status = :status AND D.label <> :label order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
		results = query.list();

		return results;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findDealsByCategoryAndStatusAndNotLabel(
			ItemCategory category, DealLabel label, ItemStatus status) {

		List<Item> results = new ArrayList<Item>();
		String hql = "FROM Item D WHERE D.status = :status AND D.label <> :label  AND category= :category order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
		query.setParameter("category", category);
		results = query.list();

		return results;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Item> findDealsByCustomer(Customer customer) {
		List<Item> results = new ArrayList<Item>();
		String hql = "from Item D WHERE D.customer = :customer";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("customer", customer);
		results = query.list();

		return results;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Item> findSimilars(Item item) {
		List<Item> results = new ArrayList<Item>();
		String hql = "from Item D WHERE D.category = :category and D.id != :id and D.status= :status order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("category", item.getCategory());
		query.setParameter("id", item.getId());
		query.setParameter("status", ItemStatus.ON);
		results = query.list();

		return results;
	}



    @SuppressWarnings("unchecked")
    @Override
    public List<Item> find(int id, String name,DealLabel label, SubCategory
                             category,ItemStatus status, String description, String terms,
                             String customerName ,String userName,String address,City city,
                             Date startDate, Date endDate,boolean notExpired,String order,
                             String asc, int pageSize, int pageNumber) {

        List<Item> results = new ArrayList<Item>();

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Item.class,"D").createAlias("D.customer", "C");
        criteria.createAlias("C.contact", "A");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);


        if (notExpired) {
            Date now = new Date();
            criteria.add(Restrictions.ge("D.validity", now));
        }


        if (startDate != null) {
            startDate = Util.getDateWithoutTime(startDate);
            criteria.add(Restrictions.ge("D.registerDate", startDate));
        }
        if (endDate != null) {
            endDate = Util.getDateWithoutTime(Util.getTomorrowDate(endDate));
            criteria.add(Restrictions.le("D.registerDate",endDate));
        }

        if(customerName !=null && !customerName.isEmpty()){
            criteria.add(Restrictions.like("C.firstName","%"+customerName+"%"));
        }
        if(address !=null && !address.isEmpty()){
            criteria.add(Restrictions.like("A.address","%"+address+"%"));
        }


        if(category !=null ){
            criteria.add(Restrictions.eq("D.category", category));
        }

        if(name !=null && !name.isEmpty()){
            criteria.add(Restrictions.like("D.name", "%" + name + "%"));
        }

        if(description !=null && !description.isEmpty()){
            criteria.add(Restrictions.like("D.description", "%" + description + "%"));
        }


        if(userName !=null && !userName.isEmpty()){
            criteria.add(Restrictions.like("C.username", "%" + userName + "%"));
        }

        if(terms !=null && !terms.isEmpty()){
            criteria.add(Restrictions.like("C.termsOfUse", "%" + terms + "%"));
        }

        if(city !=null){
            criteria.add(Restrictions.eq("A.city", city));
        }

        if(status !=null){
            criteria.add(Restrictions.eq("D.status",status));
        }

        if(label !=null){
            criteria.add(Restrictions.eq("D.label",label));
        }

        if(id >0){
            criteria.add(Restrictions.eq("D.id",id));
        }
        if(pageSize > 0) {
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
        }
        if(order !=null && !order.isEmpty() && asc != null){
            if(asc.trim().toLowerCase().equals("asc")) {
                criteria.addOrder(org.hibernate.criterion.Order.asc("D."+order.trim()));
            }else{
                criteria.addOrder(org.hibernate.criterion.Order.desc("D."+order.trim()));
            }

        }else {
            criteria.addOrder(org.hibernate.criterion.Order.desc("D.id"));
        }
        results =criteria.list();

        if (results == null)
            results = new ArrayList<Item>();
        return results;
    }

    @Override
    public int incrementVisit(Item item) {
        int visits = item.getVisits();
        visits +=1;

        Query query = sessionFactory.getCurrentSession().createQuery("update Item I set I.visits = :visits" +
                " where I.id = :id");
        query.setParameter("visits", visits);
        query.setParameter("id", item.getId());
        int result = query.executeUpdate();
        return visits;
    }

    @Override
    public int changeStatus(ItemStatus status, int itemId) {
/*        Item item = find(itemId);
        long count =item.getCategory().getCount();

        if(status == ItemStatus.ON){
            count++;
        }else if  (( item.getStatus()  == ItemStatus.ON )&&
            ( status == ItemStatus.OFF || status == ItemStatus.EXPIRED)) {
            count--;
        }*/


      //  String q = "update Item I set I.status = :status and count=:count where I.id = :id";
        String q = "update Item I set I.status = :status  where I.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("status", status);
       // query.setParameter("id", count);
        query.setParameter("id", itemId);
        int result = query.executeUpdate();

        return 0;
    }



    @Override
    @SuppressWarnings("unchecked")
    public List<Item> checkExpires(){
        int result = -1;
        Date now = new Date();
        List<Item> items = new ArrayList<Item>();
        String q = "update Item I set I.status = :status  where validity <= :now ";
        Query query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("status", ItemStatus.EXPIRED);
        query.setParameter("now", now);
         result = query.executeUpdate();

        q= "from Item I  where validity <= :now ";
        query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("now", now);
        items = query.list();

        return items;
    }

}
