package com.semsari.dao;

import com.semsari.domain.Customer;

public interface CustomerDao extends GenericDao<Customer> {
	Customer findByUserName(String username);
    Customer edit(Customer customer);
}
