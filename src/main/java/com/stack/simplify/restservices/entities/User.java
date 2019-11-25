package com.stack.simplify.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
// to tell database id is my primary key;	
@Id	
//it will automatically generates the value of an id
@GeneratedValue
private Long id;
@NotEmpty(message="Username is mandatory.")
@Column(name="USER_NAME", length=50, nullable=false,unique=true)
private String username;
@Size (min=2, message="Firstname should have atleast two characters")
@Column(name="FIRST_NAME", length=50, nullable= false)
private String firstname;
@Column(name="LAST_NAME", length=50, nullable= false)
private String lastname;
@Column(name="EMAIL", length=50, nullable= false)
private String email;
@Column(name="ROLE", length=50, nullable= false)
private String role;
@Column(name="SSN", length=50, nullable = false, unique=true)
private String ssn;
//No argument constructor
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
	super();
	this.id = id;
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.role = role;
	this.ssn = ssn;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getSsn() {
	return ssn;
}
public void setSsn(String ssn) {
	this.ssn = ssn;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
			+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
}

}
