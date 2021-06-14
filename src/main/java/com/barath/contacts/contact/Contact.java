package com.barath.contacts.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.barath.contacts.customUser.CustomUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "contact")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="json_id")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String email;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column
	private String address;
	
	@ManyToOne	
	@JoinColumn(name = "user_id", nullable = false)
	private CustomUser user;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUser() {
		return user.getId();
	}

	public void setUser(CustomUser user) {
		this.user = user;
	}

	public Contact(String name, String email, String phoneNumber, String address, CustomUser user) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.user = user;
	}
	
	public Contact() {
	}
	
	
	
	
}
