package com.semsari.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semsari.dao.PersonDao;
import com.semsari.domain.Person;
import com.semsari.service.CRUDServiceImp;
import com.semsari.util.Util;

@Service
public class PersonServiceImp  extends CRUDServiceImp<Person> implements PersonService {

	@Autowired
	PersonDao personDao;
	
	@Override
	@Transactional
	public Person findByUserName(String username) {	
		return personDao.findByUserName(username);
	}

	@Override
	@Transactional
	public int changePassword(int id, String newPassword) {
		return personDao.changePassword(id, newPassword);
	}

	@Override
	@Transactional
	public int resetPassword(int id, String newPassword) {
		
		return personDao.changePassword(id, newPassword);
	}
	

	
	
	
}
