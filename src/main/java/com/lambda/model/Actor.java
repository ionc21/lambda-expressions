package com.lambda.model;

import java.util.Objects;

public class Actor {
	public String lastName, firstName;

	public Actor(final String lastName, final String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String lastName() {
		return this.lastName;
	}

	public String firstName() {
		return this.firstName;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.lastName);
		hash = 67 * hash + Objects.hashCode(this.firstName);
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Actor other = (Actor) obj;
		if (!Objects.equals(this.lastName, other.lastName)) {
			return false;
		}
		return Objects.equals(this.firstName, other.firstName);
	}

	@Override
	public String toString() {
		return "Actor{" + "lastName=" + lastName + ", firstName=" + firstName + '}';
	}
}
