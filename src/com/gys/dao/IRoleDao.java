package com.gys.dao;

import com.gys.po.Role;

public interface IRoleDao {
	Role getRole(Long id);
	int deleteRole(Long id);
	int insertRole(Role role);
}
