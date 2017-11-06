package com.cefisi.frank.business.entities;

import static com.cefisi.frank.business.QueryNames.ADDRESS_ONE_BY_NAME;

import java.util.Objects;

import javax.persistence.*;

/**
 * Represents an address.
 * <p>
 * Class invariants:
 * <ul>
 * <li>name, street, zip code and town never {@code null}
 * </ul>
 */
@Entity
@Table(name = "t_address")
@NamedQueries({
	@NamedQuery(name = ADDRESS_ONE_BY_NAME, query = "select a from Address a where a.name = :name") })
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String street;

    private String zipCode;

    private String town;

    /**
     * Creates a new {@code Address}.
     */
    protected Address() {
	// Empty protected no-arg constructor for JPA
    }

    private Address(Builder builder) {
	// Builder's private constructor
	id = builder.id;
	name = builder.name;
	street = builder.street;
	zipCode = builder.zipCode;
	town = builder.town;
    }

    /**
     * Returns the identifier for this {@code address}.
     *
     * @return the identifier; {@code null} if not persisted
     */
    public Integer getId() {
	return id;
    }

    /**
     * Indicates whether or not this {@code address} is persisted.
     * <p>
     * A {@code null} identifier indicates a non-persisted object.
     *
     * @return {@code true} if persisted; {@code false} otherwise
     */
    public final boolean isPersisted() {
	return null != id;
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
     * Returns the street for this {@code address}.
     *
     * @return the street
     */
    public String getStreet() {
	return street;
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
     * Returns the town for this {@code address}.
     *
     * @return the town
     */
    public String getTown() {
	return town;
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
	if (!(obj instanceof Address)) {
	    return false;
	}
	Address other = (Address) obj;
	return name.equals(other.name) && street.equals(other.street)
		&& zipCode.equals(other.zipCode) && town.equals(other.town);
    }

    // Lazily initialized cached hashcode
    @Transient
    private volatile int hashcode;

    /**
     * Returns a hashcode value for this {@code address}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = hashcode;
	if (0 == hash) {
	    hashcode = Objects.hash(name, street, zipCode, town);
	}
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
	sb.append(", street=");
	sb.append(street);
	sb.append(", zipCode=");
	sb.append(zipCode);
	sb.append(", town=");
	sb.append(town);
	sb.append("}");
	return sb.toString();
    }

    /**
     * A builder to construct {@code Address} objects.
     */
    public final static class Builder {

	private Integer id;

	private String name;

	private String street;

	private String zipCode;

	private String town;

	/**
	 * Creates a new {@code Builder}.
	 */
	public Builder() {
	    // Empty constructor
	}

	/**
	 * Creates a new {@code Builder} in order to build a new {@code Address}
	 * for update.
	 *
	 * @param original
	 *        an original {@code Address} persisted instance
	 * @return a new {@code Builder} populated with given original
	 *         {@code Address} instance
	 * @throws IllegalStateException
	 *         if {@code original} is not persisted
	 */
	public static Builder forUpdate(Address original) {
	    Integer id = original.id;
	    if (null == id) {
		throw new IllegalStateException(
			"given original is not persisted");
	    }
	    Builder builder = new Builder();
	    builder.id = id;
	    builder.name = original.name;
	    builder.street = original.street;
	    builder.zipCode = original.zipCode;
	    builder.town = original.town;
	    return builder;
	}

	/**
	 * Assigns given name to this {@code builder}.
	 *
	 * @param name
	 *        a name
	 * @return this {@code builder} for chaining
	 */
	public Builder setName(String name) {
	    this.name = name;
	    return this;
	}

	/**
	 * Assigns given street to this {@code builder}.
	 *
	 * @param street
	 *        a street
	 * @return this {@code builder} for chaining
	 */
	public Builder setStreet(String street) {
	    this.street = street;
	    return this;
	}

	/**
	 * Assigns given zip code to this {@code builder}.
	 *
	 * @param code
	 *        a zip code
	 * @return this {@code builder} for chaining
	 */
	public Builder setZipCode(String code) {
	    zipCode = code;
	    return this;
	}

	/**
	 * Assigns given town to this {@code builder}.
	 *
	 * @param town
	 *        a town
	 * @return this {@code builder} for chaining
	 */
	public Builder setTown(String town) {
	    this.town = town;
	    return this;
	}

	/**
	 * Builds a new {@code Address} object with provided name, street, zip
	 * code and town.
	 * <p>
	 * This implementation ensures class invariants.
	 *
	 * @return a new {@code Address} object
	 * @throws NullPointerException
	 *         if any of the provided argument is {@code null}
	 */
	public Address build() {
	    Objects.requireNonNull(name);
	    Objects.requireNonNull(street);
	    Objects.requireNonNull(zipCode);
	    Objects.requireNonNull(town);
	    return new Address(this);
	}
    }
}
