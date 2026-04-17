package com.akash.dto;

import lombok.Data;

@Data
public class IApiResponse<T> {
	
	private Integer status;
	
	private String msg;
	
	private T payload;

}
