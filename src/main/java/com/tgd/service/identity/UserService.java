package com.tgd.service.identity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.mappers.UserMapperDTO;
import com.tgd.dto.request.identity.RegisterRequest;
import com.tgd.dto.request.identity.UserRequestDTO;
import com.tgd.dto.response.UserResponse;
import com.tgd.entity.User;
import com.tgd.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public int createUser(RegisterRequest registerRequest, Long accountId) {
		if (userRepository.getUserByPhone(registerRequest.getPhoneNumber()) != null) {
			throw new IllegalArgumentException("User's phone already exist!");
		}

		User user = new User();
		user.setAccountId(accountId);
		user.setFullName(registerRequest.getFullName());
		user.setPhoneNumber(registerRequest.getPhoneNumber());
		user.setAddress(registerRequest.getAddress());

		return userRepository.createUser(user);
	}

	public UserResponse getMyProfile(Jwt jwt) {
		if (jwt == null || jwt.getSubject() == null) {
			throw new IllegalArgumentException("Invalid token payload");
		}

		Long accountId = Long.parseLong(jwt.getSubject());
		User user = userRepository.getUserByAccountId(accountId);
		if (user == null) {
			throw new IllegalArgumentException("User profile not found");
		}

		return UserMapperDTO.toUserResponse(user);
	}

	public List<UserResponse> getAllUsers() {
		return userRepository.getAllUsers().stream().map(UserMapperDTO::toUserResponse).collect(Collectors.toList());
	}

	public UserResponse getUserByPhone(String phone) {
		return UserMapperDTO.toUserResponse(userRepository.getUserByPhone(phone));
	}

	@Transactional
	public UserResponse updateMyProfile(Jwt jwt, UserRequestDTO request) {
		if (jwt == null || jwt.getSubject() == null) {
			throw new IllegalArgumentException("Invalid token payload");
		}

		Long accountId = Long.parseLong(jwt.getSubject());
		User existingUser = userRepository.getUserByAccountId(accountId);
		if (existingUser == null) {
			throw new IllegalArgumentException("User profile not found");
		}

		// Check if phone number is changed and if the new number belongs to another
		// user
		if (!existingUser.getPhoneNumber().equals(request.getPhoneNumber())) {
			User userWithSamePhone = userRepository.getUserByPhone(request.getPhoneNumber());
			if (userWithSamePhone != null && !userWithSamePhone.getAccountId().equals(accountId)) {
				throw new IllegalArgumentException("Phone number is already taken by another user");
			}
		}

		userRepository.updateUser(accountId, request.getFullName(), request.getPhoneNumber(), request.getAddress());

		User updatedUser = new User();
		updatedUser.setFullName(request.getFullName());
		updatedUser.setPhoneNumber(request.getPhoneNumber());
		updatedUser.setAddress(request.getAddress());

		return UserMapperDTO.toUserResponse(updatedUser);
	}

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
}
