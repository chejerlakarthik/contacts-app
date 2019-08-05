package com.karthik.contacts.repository;

import com.karthik.contacts.model.Contact;
import com.karthik.contacts.model.Name;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {

    Optional<Contact> findByName(String name);
}
