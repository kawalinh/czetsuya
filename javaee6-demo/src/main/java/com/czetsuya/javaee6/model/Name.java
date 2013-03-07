package com.czetsuya.javaee6.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;

/**
 * @author Edward P. Legaspi
 * @since Sep 30, 2012
 **/
@Embeddable
public class Name implements Serializable, Cloneable {
	private static final long serialVersionUID = 6734325129441230157L;

	@Column(name = "FIRSTNAME", length = 50)
	@Length(max = 50)
	protected String firstName;

	@Column(name = "LASTNAME", length = 50)
	@Length(max = 50)
	protected String lastName;

	public Name() {
	}

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
