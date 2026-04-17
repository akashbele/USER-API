package com.akash.service;

import java.util.List;

import com.akash.dto.ICityDto;
import com.akash.dto.ICountryDto;
import com.akash.dto.IQuoteApiResponseDto;
import com.akash.dto.IResetPwdDto;
import com.akash.dto.IStateDto;
import com.akash.dto.IUserDto;

public interface IUserService {
	
	public List<ICountryDto> getCountries();
	
	public List<IStateDto> getStates(Integer countryId);
	
	public List<ICityDto> getCities(Integer stateId);
	
	public IUserDto getUserByEmail(String email);
	
	public IUserDto registerUser(IUserDto userDto);
	
	public IUserDto login(IUserDto userDto);
	
	public IUserDto resetPwd(IResetPwdDto resetPwdDto);
	
	public IQuoteApiResponseDto getQuote();
	
	public String generateTempPwd();

}
