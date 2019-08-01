package com.karthik.contacts.model;

import java.text.MessageFormat;

public class Email {
	
	private String userName;
	private String domainName;
	
	public Email() {}
	
	public Email(String userName, String domainName) {
		this.userName = userName;
		this.domainName = domainName;
	}

	public String getAddress() {
		return MessageFormat.format("{0}@{1}", userName, domainName);
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Override
	public String toString() {
		return getAddress();
	}
}
