package com.skilldistillery.film.entities;

public class Actor {
	private String firstName;
	private String lastName;
	
	public Actor(String _firstName, String _lastName) {
		firstName = _firstName;
		lastName = _lastName;
	}
	
	public void setFirstName(String _firstName) {
		firstName = _firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String _lastName) {
		lastName = _lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFullName(String _firstName, String _lastName) {
		firstName = _firstName;
		lastName = _lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
		
	}
	
	public String toString() {
		return getFullName();
	}
}
