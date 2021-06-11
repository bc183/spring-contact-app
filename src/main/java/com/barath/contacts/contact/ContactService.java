package com.barath.contacts.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactService {
	
	@Autowired
	ContactRepository contactRepository;
	
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}
	
	public boolean contactExists(Long id) {
		return contactRepository.existsById(id);
	}
	
	public Contact findContactById(Long id) {
		return contactRepository.findContactById(id);
	}
	
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}
	
	public Contact updateContact(Contact contact, Long id) {
		Contact existingContact = findContactById(id);
		existingContact.setName(contact.getName());
		existingContact.setEmail(contact.getEmail());
		existingContact.setPhoneNumber(contact.getPhoneNumber());
		existingContact.setAddress(contact.getAddress());
		return contactRepository.save(existingContact);
	}
	
	public void deleteContact(Long id) {
		 contactRepository.deleteById(id);
	}
	
}
