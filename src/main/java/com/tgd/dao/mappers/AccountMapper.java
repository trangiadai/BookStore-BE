package com.tgd.dao.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.Account;

@Mapper 
public interface AccountMapper {
    Account getAccountByEmail(Map<String, Object> param);
    
    void createAccount(Map<String, Object> param); 
    
}
