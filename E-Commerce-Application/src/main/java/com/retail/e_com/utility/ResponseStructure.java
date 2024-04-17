package com.retail.e_com.utility;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
public class ResponseStructure<T> {

	private int statuscode;
	private String message;
	private T data;
	public int getStatusCode() {
		return statuscode;
	}
	public ResponseStructure<T> setStatusCode(int statusCode) {
		this.statuscode = statusCode;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public T getData() {
		return data;
		
	}
	public ResponseStructure<T> setData(T userResponse) {
		this.data = userResponse;
		return this;
	}
	
	
	
}
