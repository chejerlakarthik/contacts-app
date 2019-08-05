package com.karthik.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;

public class Email {

	@JsonIgnore
	private String userName;
	@JsonIgnore
	private String domainName;
	private String address;

	public Email() {}

	public Email(String userName, String domainName)
	{
		this.userName = userName;
		this.domainName = domainName;
	}

	public Email(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		if (StringUtils.isEmpty(this.address))
		{
			return MessageFormat.format("{0}@{1}", userName, domainName);
		}
		return this.address;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getDomainName()
	{
		return domainName;
	}

	@Override
	public String toString()
	{
		return getAddress();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Email email = (Email) o;
		return getAddress().equals(email.getAddress());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAddress());
	}
}
