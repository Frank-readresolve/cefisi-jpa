package com.cefisi.frank.business.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents an order.
 */
public class Order {

    private int id;

    private String number;

    private Set<OrderEntry> entries;

    private LocalDateTime orderDate;

    private LocalDate deliveryDate;

    private Contact contact;

    private Address address;

    /**
     * Creates a new {@code Order}.
     */
    public Order() {
	/** Empty no-arg constructor */
    }

    /**
     * Returns the identifier for this {@code order}.
     *
     * @return the identifier
     */
    public int getId() {
	return id;
    }

    /**
     * Assigns given identifier to this {@code order}.
     *
     * @param id
     *        an identifier
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Returns the entries for this {@code order}.
     *
     * @return the entries
     */
    public Set<OrderEntry> getEntries() {
	return entries;
    }

    /**
     * Assigns given entries to this {@code order}.
     *
     * @param entries
     *        a set of entries
     */
    public void setEntries(Set<OrderEntry> entries) {
	this.entries = entries;
    }

    /**
     * Returns the order date for this {@code order}.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
	return orderDate;
    }

    /**
     * Assigns given order date to this {@code order}.
     *
     * @param date
     *        an order date
     */
    public void setOrderDate(LocalDateTime date) {
	orderDate = date;
    }

    /**
     * Returns the delivery date for this {@code order}.
     *
     * @return the delivery date
     */
    public LocalDate getDeliveryDate() {
	return deliveryDate;
    }

    /**
     * Assigns given delivery date to this {@code order}.
     *
     * @param date
     *        an delivery date
     */
    public void setDeliveryDate(LocalDate date) {
	deliveryDate = date;
    }

    /**
     * Returns the contact for this {@code order}.
     *
     * @return the contact
     */
    public Contact getContact() {
	return contact;
    }

    /**
     * Assigns given contact to this {@code order}.
     *
     * @param contact
     *        a contact
     */
    public void setContact(Contact contact) {
	this.contact = contact;
    }

    /**
     * Returns the delivery address for this {@code order}.
     *
     * @return the delivery address
     */
    public Address getAddress() {
	return address;
    }

    /**
     * Assigns given delivery address to this {@code order}.
     *
     * @param address
     *        a delivery address
     */
    public void setAddress(Address address) {
	this.address = address;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code order}.
     * <p>
     * Two {@code Order} instances are considered equal if and only if their
     * numbers are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code order};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Order)) {
	    return false;
	}
	Order other = (Order) obj;
	return number.equals(other.number);
    }

    /**
     * Returns a hashcode value for this {@code order}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = 17;
	hash += 31 * hash + number.hashCode();
	return hash;
    }

    /**
     * Returns the string representation for this {@code order}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("{id=");
	sb.append(id);
	sb.append(", number=");
	sb.append(number);
	sb.append("}");
	return sb.toString();
    }
}
