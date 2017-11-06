package com.cefisi.frank.business.entities;

import static com.cefisi.frank.business.QueryNames.CONTACT_ONE_BY_LASTNAME;

import java.util.Objects;

import javax.persistence.*;

/**
 * Represents a contact.
 * <p>
 * Class invariants:
 * <ul>
 * <li>firstname, lastname and email never {@code null}
 * </ul>
 */
@Entity
@Table(name = "t_contact")
@NamedQueries({
	@NamedQuery(name = CONTACT_ONE_BY_LASTNAME, query = "select c from Contact c where c.lastname = :lastname") })
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    /**
     * Creates a new {@code Contact}.
     */
    protected Contact() {
	// Empty protected no-arg constructor for JPA
    }

    private Contact(Builder builder) {
	// Builder's private constructor
	id = builder.id;
	firstname = builder.firstname;
	lastname = builder.lastname;
	email = builder.email;
    }

    /**
     * Returns the identifier for this {@code contact}.
     *
     * @return the identifier; {@code null} if not persisted
     */
    public Integer getId() {
	return id;
    }

    /**
     * Indicates whether or not this {@code contact} is persisted.
     * <p>
     * A {@code null} identifier indicates a non-persisted object.
     *
     * @return {@code true} if persisted; {@code false} otherwise
     */
    public final boolean isPersisted() {
	return null != id;
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
     * Returns the lastname for this {@code contact}.
     *
     * @return the lastname
     */
    public String getLastname() {
	return lastname;
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

    // Lazily initialized cached hashcode
    @Transient
    private volatile int hashcode;

    /**
     * Returns a hashcode value for this {@code contact}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = hashcode;
	if (0 == hash) {
	    hashcode = Objects.hash(email);
	}
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
	sb.append(", firstname=");
	sb.append(firstname);
	sb.append(", lastname=");
	sb.append(lastname);
	sb.append(", email=");
	sb.append(email);
	sb.append("}");
	return sb.toString();
    }

    /**
     * A builder to construct {@code Contact} objects.
     */
    public final static class Builder {

	private Integer id;

	private String firstname;

	private String lastname;

	private String email;

	/**
	 * Creates a new {@code Builder}.
	 */
	public Builder() {
	    // Empty constructor
	}

	/**
	 * Creates a new {@code Builder} in order to build a new {@code Contact}
	 * for update.
	 *
	 * @param original
	 *        an original {@code Contact} persisted instance
	 * @return a new {@code Builder} populated with given original
	 *         {@code Contact} instance
	 * @throws IllegalStateException
	 *         if {@code original} is not persisted
	 */
	public static Builder forUpdate(Contact original) {
	    Integer id = original.id;
	    if (null == id) {
		throw new IllegalStateException(
			"given original is not persisted");
	    }
	    Builder builder = new Builder();
	    builder.id = id;
	    builder.firstname = original.firstname;
	    builder.lastname = original.lastname;
	    builder.email = original.email;
	    return builder;
	}

	/**
	 * Assigns given firstname to this {@code builder}.
	 *
	 * @param name
	 *        a firstname
	 * @return this {@code builder} for chaining
	 */
	public Builder setFirstname(String name) {
	    firstname = name;
	    return this;
	}

	/**
	 * Assigns given lastname to this {@code builder}.
	 *
	 * @param name
	 *        a lastname
	 * @return this {@code builder} for chaining
	 */
	public Builder setLastname(String name) {
	    lastname = name;
	    return this;
	}

	/**
	 * Assigns given email to this {@code builder}.
	 *
	 * @param email
	 *        an email
	 * @return this {@code builder} for chaining
	 */
	public Builder setEmail(String email) {
	    this.email = email;
	    return this;
	}

	/**
	 * Builds a new {@code Contact} object with provided firstname, lastname
	 * and email.
	 * <p>
	 * This implementation ensures class invariants.
	 *
	 * @return a new {@code Contact} object
	 * @throws NullPointerException
	 *         if any of the provided argument is {@code null}
	 */
	public Contact build() {
	    Objects.requireNonNull(firstname);
	    Objects.requireNonNull(lastname);
	    Objects.requireNonNull(email);
	    return new Contact(this);
	}
    }
}
