package com.lvhong.shiro.dao;

import java.util.Map;
import java.util.Set;

import com.lvhong.shiro.pojo.User;

public interface TmSysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findUserByPhone(Map<String, String> map);

	Set<String> findRolesByUserName(String userName);

	//Set<String> findPermissionsByUserName(String userName);
}