package com.barath.contacts.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.contacts.customUser.CustomUser;
import com.barath.contacts.customUser.CustomUserDetailsService;
import com.barath.contacts.customUser.CustomUserRepository;
import com.barath.contacts.exceptions.ApiException;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	CustomUserRepository repo;
	
	
	@GetMapping()
	public ResponseEntity<List<Contact>> getAllContacts() {
		return new ResponseEntity<List<Contact>>(contactService.getContacts(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id) throws ApiException {
		if (contactService.contactExists(id) != true) {
			throw new ApiException("Contact not found");
		}
		return new ResponseEntity<Contact>(contactService.findContactById(id), HttpStatus.OK);
	}
	
	@PostMapping("{userId}")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact, @PathVariable Long userId) {
		
		repo.findById(userId).ifPresent((data) -> {
			System.out.println(data);
			contact.setUser(data);
		});
		
		return new ResponseEntity<Contact>(contactService.saveContact(contact), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Contact> updateContact(@RequestBody ContactRequest contact, @PathVariable("id") Long id) throws ApiException {
		if (contactService.contactExists(id) != true) {
			throw new ApiException("Contact not found");
		}
		
		Contact newContact = new Contact();
		newContact.setEmail(contact.getEmail());
		newContact.setAddress(newContact.getAddress());
		newContact.setName(contact.getName());
		newContact.setPhoneNumber(contact.getPhoneNumber());
		return new ResponseEntity<Contact>(contactService.updateContact(newContact, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteContact(@RequestBody Contact contact, @PathVariable("id") Long id) throws ApiException  {
		if (contactService.contactExists(id) != true) {
			throw new ApiException("Contact not found");
		}
		contactService.deleteContact(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
