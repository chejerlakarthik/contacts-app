package com.karthik.contacts.model;

import java.util.List;

public class ContactsResponse {

	private int count;
	private List<Contact> contacts;
	
	public ContactsResponse(List<Contact> contacts)
	{
		this.contacts = contacts;
		this.count = contacts.size();
	}
	
	public int getCount() {
		return count;
	}

	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
}
