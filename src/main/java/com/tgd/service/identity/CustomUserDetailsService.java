package com.tgd.service.identity;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tgd.entity.Account;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountService.getAccountByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("Account not found with email: " + email);
		}

		return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPasswordHash(),
				account.isEnabled(), true, true, true,
				Collections.singletonList(new SimpleGrantedAuthority(account.getRole())));
	}

	public CustomUserDetailsService(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

}