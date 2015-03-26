package com.semsari.dao;

import com.semsari.domain.Person;

public interface PersonDao extends GenericDao<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
}
