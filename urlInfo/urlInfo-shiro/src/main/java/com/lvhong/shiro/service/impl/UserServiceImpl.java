package com.lvhong.shiro.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.lvhong.shiro.dao.TmSysUserMapper;
import com.lvhong.shiro.pojo.User;
import com.lvhong.shiro.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	TmSysUserMapper userMapper;

	@Cacheable(value="userCache",key="'userId:'+#username")
	public User queryUserByUserName(String username) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", username);
		return userMapper.findUserByPhone(map);
	}

	public void addUser(User user) {
		userMapper.insertSelective(user);
	}

	public User findUserByphone(Map<String, String> map) {
		return userMapper.findUserByPhone(map);
	}

	public Set<String> findRolesByUserName(String userName) {
		return userMapper.findRolesByUserName(userName);
	}

	public Set<String> findPermissionsByUserName(String userName) {
		return null;
		//return userMapper.findPermissionsByUserName(userName);
	}

	
}
