package com.lvhong.shiro.service;

import java.util.Map;
import java.util.Set;

import com.lvhong.core.pojo.User;


public interface UserService {

	User queryUserByUserName(String username);

	void addUser(User user);

	User findUserByphone(Map<String, String> map);

	Set<String> findRolesByUserName(String userName);

	Set<String> findPermissionsByUserName(String userName);

}
