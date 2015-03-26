package com.semsari.service;

import com.semsari.domain.PersonRole;

public interface PersonRoleService extends CRUDService<PersonRole> {
	PersonRole findByRoleName(String roleName);
}
