package com.retail.e_com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.e_com.request_dto.OtpRequest;
import com.retail.e_com.request_dto.UserRequest;
import com.retail.e_com.response_dto.UserResponse;
import com.retail.e_com.service.UserService;
import com.retail.e_com.utility.ResponseStructure;
import com.retail.e_com.utility.SimpleResponseStructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

	private UserService userService;
	
	
	@PostMapping("/user/register")
	public ResponseEntity<SimpleResponseStructure> userRegistration(@RequestBody UserRequest userRequest)
	{
		return userService.userRegistration(userRequest);
	}
	
	@PostMapping("/verifyOTP")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(@RequestBody OtpRequest otpRequest)
	{
		return userService.verifyOTP(otpRequest);
	}
	
}
