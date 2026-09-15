package com.tgd.service.identity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgd.dto.request.identity.RegisterRequest;
import com.tgd.entity.Account;
import com.tgd.enums.AccountRole;
import com.tgd.repository.AccountRepository;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	public Long createAccount(RegisterRequest registerRequest) {
		if (accountRepository.getAccountByEmail(registerRequest.getEmail()) != null) {
			throw new IllegalArgumentException("Email is already taken!");
		}

		Account account = new Account();
		account.setEmail(registerRequest.getEmail());
		account.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
		account.setRole(AccountRole.CUSTOMER.name());
		account.setEnabled(true);

		return accountRepository.createAccount(account).longValue();
	}

	protected Account getAccountByEmail(String email) {
		return accountRepository.getAccountByEmail(email);
	}

	public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		super();
		this.accountRepository = accountRepository;
		this.passwordEncoder = passwordEncoder;
	}
}
