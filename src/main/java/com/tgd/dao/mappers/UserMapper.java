package com.tgd.dao.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.User;

@Mapper
public interface UserMapper {
	User getUserByPhone(Map<String, Object> param);

	User getUserByAccountId(Map<String, Object> param);

	List<User> getAllUsers();
	
	int updateUser(Map<String, Object> param);

	int createUser(Map<String, Object> param);
}
