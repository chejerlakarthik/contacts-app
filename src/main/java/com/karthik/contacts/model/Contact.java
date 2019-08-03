package com.karthik.contacts.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Contact {

	@Id
	private String id;
	private String name;
	private String personalEmail;
	
	public Contact() {}
	
	public Contact(String name, String personalEmail) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.personalEmail = personalEmail;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

}
