package com.karthik.contacts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.karthik.contacts.model.Contact;
import com.karthik.contacts.model.ContactsResponse;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
	
	private List<Contact> contacts = new ArrayList<>();
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Contact> addNewContact(@RequestBody Contact contactToAdd)
	{
		Contact newContact = new Contact(contactToAdd.getName(), contactToAdd.getPersonalEmail());
		contacts.add(newContact);
		return contacts;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactsResponse getAllContacts()
	{
		ContactsResponse response = new ContactsResponse(contacts);
		return response;
	}
	
	@GetMapping
	@RequestMapping("/{name}")
	public Contact getContact(@PathVariable String name)
	{
		Optional<Contact> mayBeContact = contacts.stream()
												 .filter(c -> c.getName().equalsIgnoreCase(name))
												 .findFirst();
		
		return mayBeContact.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
	}


}
