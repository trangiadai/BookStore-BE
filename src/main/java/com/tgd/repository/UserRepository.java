package com.tgd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.UserMapper;
import com.tgd.entity.User;

@Repository
public class UserRepository {
	private final UserMapper userMapper;

	public int updateUser(Long accountId, String fullName, String phoneNumber, String address) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", accountId);
		param.put("fullName", fullName);
		param.put("phoneNumber", phoneNumber);
		param.put("address", address);
		return userMapper.updateUser(param);
	}

	public User getUserByPhone(String phone) {
		Map<String, Object> param = new HashMap<>();
		param.put("phone", phone);

		return userMapper.getUserByPhone(param);
	}

	public User getUserByAccountId(Long accountId) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", accountId);
		return userMapper.getUserByAccountId(param);
	}

	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}

	public int createUser(User user) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", user.getAccountId());
		param.put("fullName", user.getFullName());
		param.put("phoneNumber", user.getPhoneNumber());
		param.put("address", user.getAddress());

		return userMapper.createUser(param);
	}

	public UserRepository(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}
}
