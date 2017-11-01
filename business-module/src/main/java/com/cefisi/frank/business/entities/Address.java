package com.cefisi.frank.business.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents an address.
 */
@Entity
public class Address {

    @Id
    private int id;

    private String name;

    private String street;

    private String zipCode;

    private String town;

    /**
     * Creates a new {@code Address}.
     */
    public Address() {
	/** Empty no-arg constructor */
    }

    /**
     * Returns the identifier for this {@code address}.
     *
     * @return the identifier
     */
    public int getId() {
	return id;
    }

    /**
     * Assigns given identifier to this {@code address}.
     *
     * @param id
     *        an identifier
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Returns the name for this {@code address}.
     *
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Assigns given name to this {@code address}.
     *
     * @param name
     *        a name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Returns the street for this {@code address}.
     *
     * @return the street
     */
    public String getStreet() {
	return street;
    }

    /**
     * Assigns given street to this {@code address}.
     *
     * @param street
     *        a street
     */
    public void setStreet(String street) {
	this.street = street;
    }

    /**
     * Returns the zip code for this {@code address}.
     *
     * @return the zip code
     */
    public String getZipCode() {
	return zipCode;
    }

    /**
     * Assigns given zip code to this {@code address}.
     *
     * @param code
     *        a zip code
     */
    public void setZipCode(String code) {
	zipCode = code;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code address}.
     * <p>
     * Two {@code Address} instances are considered equal if and only if their
     * names, street, zip codes and towns are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code address};
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
	Address other = (Address) obj;
	return name.equals(other.name) && street.equals(other.street)
		&& zipCode.equals(other.zipCode) && town.equals(other.town);
    }

    /**
     * Returns a hashcode value for this {@code address}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = 17;
	hash += 31 * hash + name.hashCode();
	hash += 31 * hash + street.hashCode();
	hash += 31 * hash + zipCode.hashCode();
	hash += 31 * hash + town.hashCode();
	return hash;
    }

    /**
     * Returns the string representation for this {@code address}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("{id=");
	sb.append(id);
	sb.append(", name=");
	sb.append(name);
	sb.append(", zipCode=");
	sb.append(zipCode);
	sb.append("}");
	return sb.toString();
    }
}
