package com.barath.contacts;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.annotation.Rollback;

import com.barath.contacts.contact.Contact;
import com.barath.contacts.customUser.CustomUser;
import com.barath.contacts.customUser.CustomUserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomUserRepositoryTest {
	@Autowired
	private CustomUserRepository customUserRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		String password = "msdhoni007";
		
		String encodedString = BCrypt.hashpw(password, BCrypt.gensalt());
		
		CustomUser customUser = new CustomUser("barath", "barath3@gmail.com", encodedString , new Date(), new ArrayList<Contact>());
		CustomUser savedUser = customUserRepository.save(customUser);
		CustomUser existUser = entityManager.find(CustomUser.class, savedUser.getId());
		
		assert(existUser.getEmail()).equals(savedUser.getEmail());
	}
}
