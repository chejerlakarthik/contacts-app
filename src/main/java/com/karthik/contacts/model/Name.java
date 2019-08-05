package com.karthik.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class Name {

    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;
    private String fullName;

    public Name() {}

    public Name(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(String name)
    {
        this.firstName = name;
        this.lastName = "";
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFullName()
    {
        if (StringUtils.isEmpty(getLastName()))
        {
            return getFirstName();
        }
        return getFirstName() + ", " + getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name another = (Name) o;
        return getFullName().equalsIgnoreCase(another.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName());
    }
}
