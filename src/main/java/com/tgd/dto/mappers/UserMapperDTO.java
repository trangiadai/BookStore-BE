package com.tgd.dto.mappers;

import com.tgd.dto.response.identity.UserResponse;
import com.tgd.entity.User;

public class UserMapperDTO {
	public static UserResponse toUserResponse(User user) {
		if(user == null) {
			return null;
		}
		
		UserResponse userResponse = new UserResponse();
		userResponse.setFullName(user.getFullName());
		userResponse.setPhoneNumber(user.getPhoneNumber());
		userResponse.setAddress(user.getAddress());
		
		return userResponse;
	}
}
