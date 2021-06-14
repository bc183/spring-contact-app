package com.barath.contacts.customUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService {
	@Autowired
	CustomUserRepository customUserRepository;
	
	public List<CustomUser> getAllUsers() {
		return customUserRepository.findAll();
	}
	
	public CustomUser saveCustomUser(CustomUser customUser) {
		return customUserRepository.save(customUser);
	}
	
	public CustomUser findUserByEmail(String email) {
		return customUserRepository.findByEmail(email);
	}
	
	public CustomUser findUserByUsername(String username) {
		return customUserRepository.findByUsername(username);
	}
}
