package com.dddd.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GmailProfile {
	
	
	private List<GmailEmails> emails;
	private GmailName name;
	public List<GmailEmails> getEmails() {
		return emails;
	}
	public void setEmails(List<GmailEmails> emails) {
		this.emails = emails;
	}
	public GmailName getName() {
		return name;
	}
	public void setName(GmailName name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "GmailProfile [emails=" + emails + ", name=" + name + "]";
	}
	
}