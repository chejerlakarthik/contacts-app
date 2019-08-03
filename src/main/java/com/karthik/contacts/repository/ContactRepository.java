package com.karthik.contacts.repository;

import com.karthik.contacts.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {

    Contact findByName(String name);
}
