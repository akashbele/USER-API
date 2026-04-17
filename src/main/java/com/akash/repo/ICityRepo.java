package com.akash.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.dto.ICityDto;
import com.akash.entity.City;

public interface ICityRepo extends JpaRepository<City, Integer>{
	
	public List<City> findByStateStateId(Integer stateId);

}
