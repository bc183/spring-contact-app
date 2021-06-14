package com.barath.contacts.customUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomUserRepository customUserRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//System.out.println("email"+ username);
		CustomUser customUser = customUserRepo.findByUsername(username);
		System.out.println(customUser);
		if (customUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUserDetails(customUser);
	}

}
