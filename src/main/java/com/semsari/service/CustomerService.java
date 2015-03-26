package com.semsari.service;

import com.semsari.domain.Customer;

public interface CustomerService extends CRUDService<Customer> {
	Customer findByUserName(String username);
}
