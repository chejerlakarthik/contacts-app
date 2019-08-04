package com.karthik.contacts.controller;

import com.karthik.contacts.model.Contact;
import com.karthik.contacts.model.ContactsResponse;
import com.karthik.contacts.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactControllerTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactController contactsController;

    private Contact newContact;

    @BeforeEach
    public void setUp() {
        newContact = new Contact("karthik", "karthik@gmail.com");
        when(contactRepository.insert(any(Contact.class))).thenReturn(newContact);
        when(contactRepository.findAll()).thenReturn(Collections.singletonList(newContact));
        when(contactRepository.findByName(anyString())).thenReturn(newContact);
    }

    @Test
    public void testAddingANewContactIsSuccessful() {
        Contact addedContact = contactsController.addNewContact(newContact);
        assertThat(addedContact.getName(), equalTo("karthik"));
    }

    @Test
    public void testFindAllContactsIsSuccessful() {
        ContactsResponse allContacts = contactsController.getAllContacts();
        assertThat(allContacts.getContacts().size(), equalTo(1));
        assertThat(allContacts.getCount(), equalTo(1));
    }

    @Test
    public void testFindingAnExistingContactIsSuccessful() {
        Contact contactByName = contactsController.getContact("karthik");
        assertThat(contactByName.getName(), equalTo("karthik"));
        assertThat(contactByName.getPersonalEmail(), equalTo("karthik@gmail.com"));
    }

    @Test
    public void testFindingAnNonExistingContactThrowsAnException() {
        when(contactRepository.findByName("invalid")).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> contactsController.getContact("invalid"));
    }
}
