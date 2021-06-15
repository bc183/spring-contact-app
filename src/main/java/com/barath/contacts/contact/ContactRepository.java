package com.barath.contacts.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	Contact findContactById(Long id);
	
	@Query(value = "SELECT * FROM contact c WHERE c.user_id = ?3 AND (c.name iLIKE %?1% OR c.phone_number iLIKE %?2%)" ,nativeQuery = true)
	List<Contact> findByNameOrPhoneNumberAnduser_idContaining(String name, String phoneNumber, Long id);
	
	List<Contact> findContactByuser_idOrderByName(Long userId);
}
