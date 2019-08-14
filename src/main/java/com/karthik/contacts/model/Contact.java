package com.karthik.contacts.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;
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

	public Contact(String id, String name, String personalEmail)
	{
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
}
