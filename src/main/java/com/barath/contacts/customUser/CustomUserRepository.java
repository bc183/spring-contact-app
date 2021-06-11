package com.barath.contacts.customUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long>{
	CustomUser findByUsername(String username);
	Optional<CustomUser> findById(Long id);
}
