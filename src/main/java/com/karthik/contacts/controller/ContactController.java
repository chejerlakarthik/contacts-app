package com.karthik.contacts.controller;

import com.karthik.contacts.model.Contact;
import com.karthik.contacts.model.ContactsResponse;
import com.karthik.contacts.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	private final ContactRepository contactRepository;

	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@PostMapping(path = "/contacts",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public Contact addNewContact(@RequestBody Contact contactToAdd)
	{
		LOGGER.debug("Adding a new contact: " + contactToAdd);
		Contact newContact = new Contact(contactToAdd.getName(), contactToAdd.getPersonalEmail());
		return contactRepository.insert(newContact);
	}

	@GetMapping(path = "/contacts",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactsResponse getAllContacts()
	{
		LOGGER.debug("Retrieving all contacts");
		List<Contact> contacts = contactRepository.findAll();
		return new ContactsResponse(contacts);
	}
	
	@GetMapping(path = "/contacts/{id}")
	public Contact getContact(@PathVariable String id)
	{
		return contactRepository.findById(id)
								.orElseThrow(
										() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found")
								);
	}

	@PutMapping(path = "/contacts/{id}")
	public Contact updateContact(@PathVariable String id, @RequestBody Contact contactToUpdate)
	{
		return contactRepository.findById(id)
								.map(contact -> {
									contact.setName(contactToUpdate.getName());
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

	@DeleteMapping(path = "/contacts/{id}")
	public void deleteContact(@PathVariable String id)
	{
		contactRepository.findById(id)
						 .ifPresent(contactRepository::delete);
	}

}
