package com.barath.contacts.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	Contact findContactById(Long id);
	
	List<Contact> findContactByuser_idOrderByName(Long userId);
}
