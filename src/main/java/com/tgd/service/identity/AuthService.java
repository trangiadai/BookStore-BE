package com.tgd.service.identity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.request.identity.AuthRequest;
import com.tgd.dto.request.identity.IntrospectRequest;
import com.tgd.dto.request.identity.RegisterRequest;
import com.tgd.dto.response.identity.AuthResponse;
import com.tgd.dto.response.identity.IntrospectResponse;
import com.tgd.entity.Account;

@Service
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final AccountService accountService;
	private final UserService userService;
	
	public AuthResponse login(AuthRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		Account account = accountService.getAccountByEmail(request.getEmail());
		String token = tokenService.generateToken(account.getId(), account.getEmail(), account.getRole());

		return new AuthResponse(token);
	}

	@Transactional
	public int registerCustomer(RegisterRequest registerRequest) {
		Long accountId = accountService.createAccount(registerRequest);
		int createdUserCount = userService.createUser(registerRequest, accountId);
			
		return createdUserCount;
	}
	
	public IntrospectResponse introspect(IntrospectRequest request) {
	    return tokenService.introspect(request);
	}
	
	public void logout(String token) {
	    // Strip "Bearer " prefix if provided
	    if (token != null && token.startsWith("Bearer ")) {
	        token = token.substring(7);
	    }
	    tokenService.logout(token);
	}

	public AuthService(AuthenticationManager authenticationManager, AccountService accountService,
			TokenService tokenService, UserService userService) {
		super();
		this.authenticationManager = authenticationManager;
		this.accountService = accountService;
		this.tokenService = tokenService;
		this.userService = userService;
	}
}
