package com.tgd.configuration;

import com.tgd.dao.mappers.AccountMapper;
import com.tgd.dao.mappers.UserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationInitConfig {

	private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);

	@Value("${app.init.admin.email}")
	private String adminEmail;

	@Value("${app.init.admin.password}")
	private String adminPassword;

	@Bean
	@Transactional
	ApplicationRunner applicationRunner(AccountMapper accountMapper, UserMapper userMapper,
			PasswordEncoder passwordEncoder) {
		return args -> {
			Map<String, Object> queryParam = new HashMap<>();
			queryParam.put("email", adminEmail);

			if (accountMapper.getAccountByEmail(queryParam) == null) {
				Map<String, Object> accountParam = new HashMap<>();
				accountParam.put("email", adminEmail);
				accountParam.put("passwordHash", passwordEncoder.encode(adminPassword));
				accountParam.put("role", "ADMIN");
				accountParam.put("enabled", true);

				accountMapper.createAccount(accountParam);
				Long accountId = ((Number) accountParam.get("id")).longValue();

				Map<String, Object> userParam = new HashMap<>();
				userParam.put("accountId", accountId);
				userParam.put("fullName", "System Administrator");
				userParam.put("phoneNumber", "0000000000");
				userParam.put("address", "System Headquarters");

				userMapper.createUser(userParam);

				log.warn("DEFAULT ADMIN CREATED -> Email: {} | Change password immediately!", adminEmail);
			}
		};
	}
}
