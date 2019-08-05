package com.karthik.contacts.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

public class Contact {

	@Id
	private String id;
	private String name;
	private String personalEmail;

	public Contact() {};

	public Contact(String name, String personalEmail) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.personalEmail = personalEmail;
	}

	public Contact(String id, String name, String personalEmail)
	{
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

	public Contact withEmail(String email)
	{
		return new Contact(getId(), getName(), email);
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Contact contact = (Contact) o;
		return id.equals(contact.id) &&
				name.equals(contact.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Contact{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", personalEmail='" + personalEmail + '\'' +
				'}';
	}
}
