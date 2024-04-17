package com.retail.e_com.service;

import org.springframework.http.ResponseEntity;

import com.retail.e_com.request_dto.OtpRequest;
import com.retail.e_com.request_dto.UserRequest;
import com.retail.e_com.response_dto.UserResponse;
import com.retail.e_com.utility.ResponseStructure;
import com.retail.e_com.utility.SimpleResponseStructure;

public interface UserService {

	ResponseEntity<SimpleResponseStructure> userRegistration(UserRequest userService);

	ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpRequest);

}
