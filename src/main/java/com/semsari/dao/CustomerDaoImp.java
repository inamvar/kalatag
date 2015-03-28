package com.semsari.dao;

import java.util.ArrayList;
import java.util.List;

import com.semsari.domain.ItemStatus;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semsari.domain.Customer;

@Repository
public class CustomerDaoImp extends GenericDaoImp<Customer>  implements CustomerDao{ 
	@Autowired
	private SessionFactory sessionFactory;
	

	
	@SuppressWarnings("unchecked")
	@Override
	public Customer findByUserName(String username) {
 
		List<Customer> customers = new ArrayList<Customer>();
 
		customers = sessionFactory.getCurrentSession()
			.createQuery("from Customer where username=?")
			.setParameter(0, username)
			.list();
 
		if (customers.size() > 0) {
			return (Customer) customers.get(0);
		} else {
			return null;
		}
 
	}

    @Override
    public Customer edit(Customer customer) {

        int result = -1;

        String q = "update Customer C set C.firstName = :firstName , C.lastName = :lastName  " +
                "   where C.id = :id ";
        Query query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("id",customer.getId());
        query.setParameter("firstName",customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        query.executeUpdate();
        q= "update Contact C set C.phone = :phone , C.mobile = :mobile , " +
                " C.address = :address  where C.id = :id ";
        query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("id",customer.getContact().getId());
        query.setParameter("phone",customer.getContact().getPhone());
        query.setParameter("mobile",customer.getContact().getMobile());
        query.setParameter("address",customer.getContact().getAddress());

        result = query.executeUpdate();

        return customer;

    }
}
