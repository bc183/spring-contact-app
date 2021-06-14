package com.barath.contacts.customUser;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.barath.contacts.contact.Contact;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "users")	
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="json_id")
public class CustomUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false, unique = true)																																																																																																																																																																																																			
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Date createdAt;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
	private List<Contact> contacts;
	
	@PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
    }

	public Long getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public CustomUser() {
	}
	
	public CustomUser(String username, String email, String password, Date createdAt, List<Contact> contacts) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.contacts = contacts;
	}


	
	
	
}
