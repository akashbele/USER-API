package com.akash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.entity.Country;

public interface ICountryRepo extends JpaRepository<Country, Integer>{

}
