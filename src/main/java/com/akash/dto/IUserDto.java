package com.akash.dto;

import lombok.Data;

@Data
public class IUserDto {

	private Integer userId;

	private String userName;

	private String email;

	private String phno;
	
	private String pwd;

	private String pwdUpdated;
	
	private Integer countryId;
	
	private Integer stateId;
	
	private Integer cityId;

}
