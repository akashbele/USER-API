package com.akash.dto;

import lombok.Data;

@Data
public class IResetPwdDto {
	
	private String email;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String confirmPassword;

}
