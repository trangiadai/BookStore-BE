package com.tgd.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.AccountMapper;
import com.tgd.entity.Account;

@Repository
public class AccountRepository {
	private final AccountMapper accountMapper;

	public Account getAccountByEmail(String email) {
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);

		return accountMapper.getAccountByEmail(param);
	}

    public Number createAccount(Account account) {
    	Map<String, Object> param = new HashMap<>();
		param.put("email", account.getEmail());
		param.put("passwordHash", account.getPasswordHash());
		param.put("role", account.getRole());
		param.put("enabled", account.isEnabled());
		accountMapper.createAccount(param);
		
		return (Number) param.get("id");
    }
    
	public AccountRepository(AccountMapper accountMapper) {
		super();
		this.accountMapper = accountMapper;
	}

}