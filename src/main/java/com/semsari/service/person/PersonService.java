package com.semsari.service.person;

import com.semsari.domain.Person;
import com.semsari.service.CRUDService;

public interface PersonService  extends CRUDService<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
	int resetPassword(int id, String newPassword);
	
	
}
