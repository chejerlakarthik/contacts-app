package com.karthik.contacts.controller;

import com.karthik.contacts.model.Contact;
import com.karthik.contacts.model.ContactsResponse;
import com.karthik.contacts.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	private final ContactRepository contactRepository;

	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Contact addNewContact(@RequestBody Contact contactToAdd)
	{
		Contact newContact = new Contact(contactToAdd.getName(), contactToAdd.getPersonalEmail());
		return contactRepository.insert(newContact);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactsResponse getAllContacts()
	{
		List<Contact> contacts = contactRepository.findAll();
		return new ContactsResponse(contacts);
	}
	
	@GetMapping(path = "/{name}")
	public Contact getContact(@PathVariable String name)
	{
		return contactRepository.findByName(name)
								.orElseThrow(
										() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found")
								);
	}

	@PutMapping(path = "/{name}")
	public Contact updateContact(@PathVariable String name, @RequestBody Contact contactToUpdate)
	{
		return contactRepository.findByName(name)
								.map(contact -> {
									contact.setPersonalEmail(contactToUpdate.getPersonalEmail());
									return contact;
								})
								.map(contactRepository::save)
								.orElseThrow(
									 () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found")
								);
	}

	@DeleteMapping
	public void deleteAll()
	{
		contactRepository.deleteAll();
	}

	@DeleteMapping(path = "/{name}")
	public void deleteContact(@PathVariable String name)
	{
		contactRepository.findByName(name)
						 .ifPresent(contactRepository::delete);
	}

}
