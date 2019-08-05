package com.karthik.contacts.model;

import java.util.Objects;

public class ContactId {

    private String id;

    public ContactId(String id)
    {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactId contactId = (ContactId) o;
        return id.equals(contactId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
