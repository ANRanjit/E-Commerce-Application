package com.retail.e_com.request_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequest {

	private String email;
	private int otp;
}
