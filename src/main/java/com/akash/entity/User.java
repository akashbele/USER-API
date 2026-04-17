package com.akash.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String userName;
	
	private String email;
	
	private String phno;
	
	private String pwd;
	
	private String pwdUpdated;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate created_at;
	
	@UpdateTimestamp
	@Column(updatable = true)
	private LocalDate updated_at;

}
