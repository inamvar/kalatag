package com.semsari.dao;

import com.semsari.domain.PersonRole;

public interface PersonRoleDao extends GenericDao<PersonRole>{
	PersonRole findByRoleName(String roleName);
}
