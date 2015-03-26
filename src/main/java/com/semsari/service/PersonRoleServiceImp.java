package com.semsari.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semsari.dao.PersonRoleDao;
import com.semsari.domain.PersonRole;

@Service
public class PersonRoleServiceImp extends CRUDServiceImp<PersonRole> implements PersonRoleService{

	@Autowired
	PersonRoleDao personRoleDao;
	
	@Override
	public PersonRole findByRoleName(String roleName) {	
		return personRoleDao.findByRoleName(roleName);
	}
	
}
