package com.tgd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.identity.AuthRequest;
import com.tgd.dto.request.identity.IntrospectRequest;
import com.tgd.dto.request.identity.RegisterRequest;
import com.tgd.dto.response.identity.AuthResponse;
import com.tgd.dto.response.identity.IntrospectResponse;
import com.tgd.service.identity.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody AuthRequest request) {
		return authService.login(request);
	}
	
	
	@PostMapping("/register")
	public int registerCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
		return authService.registerCustomer(registerRequest);
	}
	
	@PostMapping("/introspect")
    public IntrospectResponse introspect(@Valid @RequestBody IntrospectRequest request) {
        return authService.introspect(request);
    }
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestHeader("Authorization") String bearerToken) {
	    authService.logout(bearerToken);
	    return ResponseEntity.ok().build();
	}

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
}