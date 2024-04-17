package com.retail.e_com.response_dto;


import com.retail.e_com.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

	private int userId;
	private String displayName;
	private String userName;
	private String email;
	private UserRole userRole;
	private Boolean isEmailVerified; 
	private Boolean isDeleted;
	
}
