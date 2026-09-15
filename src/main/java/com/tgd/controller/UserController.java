package com.tgd.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.identity.UserRequestDTO;
import com.tgd.dto.response.UserResponse;
import com.tgd.service.identity.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@GetMapping("/profile")
	public UserResponse getMyProfile(@AuthenticationPrincipal Jwt jwt) {
		return userService.getMyProfile(jwt);
	}

	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/search")
	public UserResponse getUserByPhone(@RequestParam("phone") String phone) {
		return userService.getUserByPhone(phone);
	}

	@PutMapping("/profile")
	public UserResponse updateMyProfile(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody UserRequestDTO request) {
		return userService.updateMyProfile(jwt, request);
	}

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
}
