package com.semsari.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semsari.dao.CustomerDao;
import com.semsari.domain.Customer;
import com.semsari.domain.PersonRole;

@Service
public class CustomerServiceImp extends CRUDServiceImp<Customer> implements
		CustomerService {

	@Autowired
	CustomerDao CustomerDao;

	@Autowired
	PersonRoleService roleService;

	@Override
	@Transactional
	public Customer findByUserName(String username) {
		return CustomerDao.findByUserName(username);
	}

	@Override
	@Transactional
	public Customer create(Customer customer) {
		PersonRole role = roleService.findByRoleName("ROLE_CUSTOMER");
		if (role == null) {
			PersonRole r = new PersonRole();
			r.setRole("ROLE_CUSTOMER");
			r = roleService.create(r);
			customer.setPersonRole(r);
		} else {
			customer.setPersonRole(role);
		}
		customer.setEnabled(true);
		customer.getContact().setEmail(customer.getUsername());
		
		return CustomerDao.create(customer);
	}

}
