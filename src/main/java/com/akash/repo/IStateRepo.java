package com.akash.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.entity.State;

public interface IStateRepo extends JpaRepository<State, Integer>{
	
	public List<State> findByCountryCountryId(Integer countryId);

}
