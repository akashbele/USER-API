package com.akash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.dto.IApiResponse;
import com.akash.dto.ICityDto;
import com.akash.dto.ICountryDto;
import com.akash.dto.IQuoteApiResponseDto;
import com.akash.dto.IResetPwdDto;
import com.akash.dto.IStateDto;
import com.akash.dto.IUserDto;
import com.akash.service.IUserService;

@RestController
public class IUserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/Countries")
	public ResponseEntity<IApiResponse<List<ICountryDto>>> getCountries(){
		
		List<ICountryDto> countries = userService.getCountries();
		
		IApiResponse<List<ICountryDto>> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("Featched Countries");
		apiResponse.setPayload(countries);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/states/{countryId}")
	public ResponseEntity<IApiResponse<List<IStateDto>>> getStates(@PathVariable Integer countryId){
		
		List<IStateDto> states = userService.getStates(countryId);
		
		IApiResponse<List<IStateDto>> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("Featched Countries");
		apiResponse.setPayload(states);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<IApiResponse<List<ICityDto>>> getCities(@PathVariable Integer stateId){
		
		List<ICityDto> cities = userService.getCities(stateId);
		
		IApiResponse<List<ICityDto>> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("Featched Countries");
		apiResponse.setPayload(cities);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/user/{email}")
	public ResponseEntity<IApiResponse<IUserDto>> getUser(@PathVariable String email){
		
		IUserDto userByEmail = userService.getUserByEmail(email);
		
		IApiResponse<IUserDto> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("User Featched");
		apiResponse.setPayload(userByEmail);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<IApiResponse<IUserDto>> saveUser(@RequestBody IUserDto userDto){
		
		IUserDto registerUser = userService.registerUser(userDto);
		
		IApiResponse<IUserDto> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(201);
		apiResponse.setMsg("User Created");
		apiResponse.setPayload(registerUser);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<IApiResponse<IUserDto>> loginUser(@RequestBody IUserDto userDto){
		
		IUserDto login = userService.login(userDto);
		
		IApiResponse<IUserDto> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("User Login");
		apiResponse.setPayload(login);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
	}
	
	@PostMapping("/reset-pwd")
	public ResponseEntity<IApiResponse<IUserDto>> loginUser( IResetPwdDto resetPwdDto){
		
		IUserDto resetPwd = userService.resetPwd(resetPwdDto);
		
		IApiResponse<IUserDto> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("User Login");
		apiResponse.setPayload(resetPwd);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/quote")
	public ResponseEntity<IApiResponse<IQuoteApiResponseDto>> getQuote(){
		
		 IQuoteApiResponseDto quote = userService.getQuote();
		
		IApiResponse<IQuoteApiResponseDto> apiResponse = new IApiResponse<>();
		
		apiResponse.setStatus(200);
		apiResponse.setMsg("Random Quote");
		apiResponse.setPayload(quote);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
	}

}
