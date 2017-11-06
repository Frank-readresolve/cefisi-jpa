package com.cefisi.frank.business.entities;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import javax.persistence.*;

/**
 * Represents an order.
 * <p>
 * Class invariants:
 * <ul>
 * <li>reference, entries, order date, delivery date and address never
 * {@code null}
 * <li>entries never empty, at least one order entry
 * <li>delivery date greater than order date
 * </ul>
 */
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reference;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderEntry> entries;

    private LocalDateTime orderDate;

    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_contact_id"))
    private Contact contact;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_address_id"))
    private Address address;

    /**
     * Creates a new {@code Order}.
     */
    protected Order() {
	// Empty protected no-arg constructor for JPA
    }

    private Order(Builder builder) {
	// Builder's private constructor
	id = builder.id;
	reference = builder.reference;
	orderDate = builder.orderDate;
	deliveryDate = builder.deliveryDate;
	contact = builder.contact;
	address = builder.address;
    }

    private void setEntries(Set<OrderEntry> entries) {
	// Builder's private setter
	this.entries = entries;
    }

    /**
     * Returns the identifier for this {@code order}.
     *
     * @return the identifier; {@code null} if not persisted
     */
    public Integer getId() {
	return id;
    }

    /**
     * Indicates whether or not this {@code order} is persisted.
     * <p>
     * A {@code null} identifier indicates a non-persisted object.
     *
     * @return {@code true} if persisted; {@code false} otherwise
     */
    public final boolean isPersisted() {
	return null != id;
    }

    /**
     * Returns the reference for this {@code order}.
     *
     * @return the reference
     */
    public String getReference() {
	return reference;
    }

    /**
     * Returns the entries for this {@code order}.
     * <p>
     * This implementation returns a copy of the backed {@code Set}.
     *
     * @return the entries
     */
    public Set<OrderEntry> getEntries() {
	return new HashSet<>(entries);
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
     * Returns the delivery date for this {@code order}.
     *
     * @return the delivery date
     */
    public LocalDate getDeliveryDate() {
	return deliveryDate;
    }

    /**
     * Returns the contact for this {@code order}.
     *
     * @return the contact; may be {@code null}
     */
    public Contact getContact() {
	return contact;
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
     * Indicates whether or not given object is "equal to" this {@code order}.
     * <p>
     * Two {@code Order} instances are considered equal if and only if their
     * references are equal.
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
	return reference.equals(other.reference);
    }

    // Lazily initialized cached hashcode
    @Transient
    private volatile int hashcode;

    /**
     * Returns a hashcode value for this {@code order}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = hashcode;
	if (0 == hash) {
	    hashcode = Objects.hash(reference);
	}
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
	sb.append(", ref=");
	sb.append(reference);
	sb.append(", orderDate=");
	sb.append(orderDate);
	sb.append(", deliveryDate=");
	sb.append(deliveryDate);
	sb.append(", entries.size=");
	sb.append(entries.size());
	sb.append("}");
	return sb.toString();
    }

    /**
     * A builder to construct {@code Order} objects.
     */
    public final static class Builder {

	private Integer id;

	private String reference;

	private Set<OrderEntry.Builder> entryBuilders = new HashSet<>(5);

	private LocalDateTime orderDate;

	private LocalDate deliveryDate;

	private Contact contact;

	private Address address;

	/**
	 * Creates a new {@code Builder}.
	 */
	public Builder() {
	    // Empty constructor
	}

	/**
	 * Creates a new {@code Builder} in order to build a new {@code Order}
	 * for update.
	 *
	 * @param original
	 *        an original {@code Order} persisted instance
	 * @return a new {@code Builder} populated with given original
	 *         {@code Order} instance
	 * @throws IllegalStateException
	 *         if {@code original} is not persisted
	 */
	public static Builder forUpdate(Order original) {
	    Integer id = original.id;
	    if (null == id) {
		throw new IllegalStateException(
			"given original is not persisted");
	    }
	    Builder builder = new Builder();
	    builder.id = id;
	    builder.reference = original.reference;
	    builder.orderDate = original.orderDate;
	    builder.deliveryDate = original.deliveryDate;
	    builder.contact = original.contact;
	    builder.address = original.address;
	    Set<OrderEntry.Builder> entryBuilders = builder.entryBuilders;
	    for (OrderEntry entry : original.entries) {
		entryBuilders.add(OrderEntry.Builder.forUpdate(entry));
	    }
	    return builder;
	}

	/**
	 * Adds a new order entry with given article and quantity.
	 *
	 * @param article
	 *        an article
	 * @param quantity
	 *        a quantity
	 * @return this {@code builder} for chaining
	 */
	public Builder addEntry(Article article, BigDecimal quantity) {
	    OrderEntry.Builder entryBuilder = new OrderEntry.Builder();
	    entryBuilder.setArticle(article);
	    entryBuilder.setQuantity(quantity);
	    entryBuilders.add(entryBuilder);
	    return this;
	}

	/**
	 * Assigns given reference to this {@code builder}.
	 *
	 * @param ref
	 *        a reference
	 * @return this {@code builder} for chaining
	 */
	public Builder setReference(String ref) {
	    reference = ref;
	    return this;
	}

	/**
	 * Assigns given order date to this {@code builder}.
	 *
	 * @param date
	 *        an order date
	 * @return this {@code builder} for chaining
	 */
	public Builder setOrderDate(LocalDateTime date) {
	    orderDate = date;
	    return this;
	}

	/**
	 * Assigns given deliver date to this {@code builder}.
	 *
	 * @param date
	 *        a deliver date
	 * @return this {@code builder} for chaining
	 */
	public Builder setDeliveryDate(LocalDate date) {
	    deliveryDate = date;
	    return this;
	}

	/**
	 * Assigns given contact to this {@code builder}.
	 *
	 * @param contact
	 *        a contact
	 * @return this {@code builder} for chaining
	 */
	public Builder setContact(Contact contact) {
	    this.contact = contact;
	    return this;
	}

	/**
	 * Assigns given address to this {@code builder}.
	 *
	 * @param address
	 *        an address
	 * @return this {@code builder} for chaining
	 */
	public Builder setAddress(Address address) {
	    this.address = address;
	    return this;
	}

	/**
	 * Builds a new {@code Order} object with provided rate and start date.
	 * <p>
	 * This implementation ensures class invariants.
	 *
	 * @return a new {@code Order} object
	 * @throws NullPointerException
	 *         if any of the provided argument but the contact is
	 *         {@code null}, , or if any of the order entries provided
	 *         argument is {@code null}
	 * @throws IllegalArgumentException
	 *         if order date is after today, or if the delivery date is not
	 *         after the order date, or if any of the order entries quantity
	 *         is not positive
	 * @throws IllegalStateException
	 *         in case of a duplicate entry within the order entries
	 * @see OrderEntry
	 * @see OrderEntry#equals(Object)
	 */
	public Order build() {
	    Objects.requireNonNull(reference);
	    Objects.requireNonNull(address);
	    LocalDate odld = orderDate.toLocalDate();
	    LocalDate now = LocalDate.now();
	    if (odld.isAfter(now)) {
		throw new IllegalArgumentException("order date [" + odld
			+ "] is after today [" + now + "]");
	    }
	    if (!deliveryDate.isAfter(odld)) {
		throw new IllegalArgumentException(
			"delivery date [" + deliveryDate
				+ "] is not after order date [" + odld + "]");
	    }
	    Order result = new Order(this);
	    Set<OrderEntry> entries = new HashSet<>(entryBuilders.size());
	    for (OrderEntry.Builder builder : entryBuilders) {
		builder.setOrder(result);
		OrderEntry entry = builder.build();
		if (entries.contains(entry)) {
		    throw new IllegalStateException(
			    "duplicate entry [" + entry + "]");
		}
		entries.add(entry);
	    }
	    result.setEntries(entries);
	    return result;
	}
    }
}
