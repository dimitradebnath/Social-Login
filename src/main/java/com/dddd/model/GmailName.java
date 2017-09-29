package com.dddd.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GmailName {
private String familyName;
private String givenName;
public String getFamilyName() {
	return familyName;
}
public void setFamilyName(String familyName) {
	this.familyName = familyName;
}
public String getGivenName() {
	return givenName;
}
public void setGivenName(String givenName) {
	this.givenName = givenName;
}
@Override
public String toString() {
	return "GmailName [familyName=" + familyName + ", givenName=" + givenName + "]";
}

}
