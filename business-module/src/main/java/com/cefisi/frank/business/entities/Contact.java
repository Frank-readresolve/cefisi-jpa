package com.cefisi.frank.business.entities;

/**
 * Represents a contact.
 */
public class Contact {

    private int id;

    private String firstname;

    private String lastname;

    private String email;

    /**
     * Creates a new {@code Contact}.
     */
    public Contact() {
	/** Empty no-arg constructor */
    }

    /**
     * Returns the identifier for this {@code contact}.
     *
     * @return the identifier
     */
    public int getId() {
	return id;
    }

    /**
     * Assigns given identifier to this {@code contact}.
     *
     * @param id
     *        an identifier
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Returns the firstname for this {@code contact}.
     *
     * @return the firstname
     */
    public String getFirstname() {
	return firstname;
    }

    /**
     * Assigns given firstname to this {@code contact}.
     *
     * @param name
     *        a firstname
     */
    public void setFirstname(String name) {
	firstname = name;
    }

    /**
     * Returns the lastname for this {@code contact}.
     *
     * @return the lastname
     */
    public String getLastname() {
	return lastname;
    }

    /**
     * Assigns given lastname to this {@code contact}.
     *
     * @param name
     *        a lastname
     */
    public void setLastname(String name) {
	lastname = name;
    }

    /**
     * Returns the email address for this {@code contact}.
     *
     * @return the email address
     */
    public String getEmail() {
	return email;
    }

    /**
     * Assigns given email address to this {@code contact}.
     *
     * @param address
     *        an email address
     */
    public void setEmail(String address) {
	email = address;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code contact}.
     * <p>
     * Two {@code Contact} instances are considered equal if and only if their
     * emails are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code contact};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Contact)) {
	    return false;
	}
	Contact other = (Contact) obj;
	return email.equals(other.email);
    }

    /**
     * Returns a hashcode value for this {@code contact}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = 17;
	hash += 31 * hash + email.hashCode();
	return hash;
    }

    /**
     * Returns the string representation for this {@code contact}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("{id=");
	sb.append(id);
	sb.append(", email=");
	sb.append(email);
	sb.append("}");
	return sb.toString();
    }
}
