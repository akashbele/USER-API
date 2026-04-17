package com.akash.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akash.dto.ICityDto;
import com.akash.dto.ICountryDto;
import com.akash.dto.IQuoteApiResponseDto;
import com.akash.dto.IResetPwdDto;
import com.akash.dto.IStateDto;
import com.akash.dto.IUserDto;
import com.akash.entity.City;
import com.akash.entity.Country;
import com.akash.entity.State;
import com.akash.entity.User;
import com.akash.repo.ICityRepo;
import com.akash.repo.ICountryRepo;
import com.akash.repo.IStateRepo;
import com.akash.repo.IUserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IUserServiceImpl implements IUserService {

	private ICountryRepo countryRepo;

	private IStateRepo stateRepo;

	private ICityRepo cityRepo;

	private IUserRepo userRepo;

	private ModelMapper mapper;

	private IEmailService emailService;

	@Override
	public List<ICountryDto> getCountries() {

		List<Country> countries = countryRepo.findAll();

		return countries.stream().map(country -> mapper.map(country, ICountryDto.class)).toList();
	}

	@Override
	public List<IStateDto> getStates(Integer countryId) {

		List<State> states = stateRepo.findByCountryCountryId(countryId);

		return states.stream().map(state -> mapper.map(state, IStateDto.class)).toList();
	}

	@Override
	public List<ICityDto> getCities(Integer stateId) {

		List<City> cities = cityRepo.findByStateStateId(stateId);

		return cities.stream().map(city -> mapper.map(city, ICityDto.class)).toList();
	}

	@Override
	public IUserDto getUserByEmail(String email) {

		Optional<User> byEmail = userRepo.findByEmail(email);

		if (byEmail.isPresent()) {
			User user = byEmail.get();

			return mapper.map(user, IUserDto.class);
		}
		return null;
	}

	@Override
	public IUserDto registerUser(IUserDto userDto) {

		User entity = mapper.map(userDto, User.class);

		Country country = countryRepo.findById(userDto.getCountryId()).orElseThrow();

		State state = stateRepo.findById(userDto.getStateId()).orElseThrow();

		City city = cityRepo.findById(userDto.getCityId()).orElseThrow();

		entity.setCountry(country);
		entity.setState(state);
		entity.setCity(city);

		entity.setPwdUpdated("NO");

		String pwd = generateTempPwd();
		entity.setPwd(pwd);

		User savedEntiry = userRepo.save(entity);

		String subject = "Your Account Created | AB";
		String body = "Your Login Password :" + pwd;

		emailService.sendEmail(userDto.getEmail(), subject, body);

		return mapper.map(savedEntiry, IUserDto.class);
	}

	@Override
	public IUserDto login(IUserDto userDto) {

		Optional<User> user = userRepo.findByEmailAndPwd(userDto.getEmail(), userDto.getPwd());

		if (user.isPresent()) {
			return mapper.map(user.get(), IUserDto.class);
		}
		return null;
	}

	@Override
	public IUserDto resetPwd(IResetPwdDto resetPwdDto) {

		Optional<User> byEmail = userRepo.findByEmail(resetPwdDto.getEmail());

		if (byEmail.isPresent()) {
			User user = byEmail.get();
			user.setPwd(resetPwdDto.getNewPassword());
			user.setPwdUpdated("Yes");

			User save = userRepo.save(user);

			return mapper.map(save, IUserDto.class);
		}
		return null;
	}

	@Override
	public IQuoteApiResponseDto getQuote() {

		String apiUrl = "https://dummyjson.com/quotes/random";

		RestTemplate template = new RestTemplate();
		ResponseEntity<IQuoteApiResponseDto> entity = template.getForEntity(apiUrl, IQuoteApiResponseDto.class);

		return entity.getBody();
	}

	@Override
	public String generateTempPwd() {

		StringBuffer buffer = new StringBuffer(6);

		Random random = new Random();

		String chars = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";

		for (int i = 0; i < 6; i++) {
			int randomIndex = random.nextInt(chars.length());
			char charAt = chars.charAt(randomIndex);

			buffer.append(charAt);
		}

		return buffer.toString();

	}

}
