package com.jsp.medishop.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseStructure<T> {

	private int statusCode;
	private String StatusMessage;
	private T statusData;
}
