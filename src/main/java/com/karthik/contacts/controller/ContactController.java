package com.karthik.contacts.controller;

import com.karthik.contacts.model.Contact;
import com.karthik.contacts.model.ContactsResponse;
import com.karthik.contacts.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

	private final ContactRepository contactRepository;

	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@PostMapping(path = "/contacts",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public Contact addNewContact(@RequestBody Contact contactToAdd)
	{
		Contact newContact = new Contact(contactToAdd.getName(), contactToAdd.getPersonalEmail());
		return contactRepository.insert(newContact);
	}

	@GetMapping(path = "/contacts",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactsResponse getAllContacts()
	{
		List<Contact> contacts = contactRepository.findAll();
		return new ContactsResponse(contacts);
	}
	
	@GetMapping(path = "/contacts/{name}")
	public Contact getContact(@PathVariable String name)
	{
		return contactRepository.findByName(name)
								.orElseThrow(
										() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found")
								);
	}

	@PutMapping(path = "/contacts/{name}")
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

	@DeleteMapping(path = "/contacts")
	public void deleteAll()
	{
		contactRepository.deleteAll();
	}

	@DeleteMapping(path = "/contacts/{name}")
	public void deleteContact(@PathVariable String name)
	{
		contactRepository.findByName(name)
						 .ifPresent(contactRepository::delete);
	}

}
