package com.cefisi.frank.business.entities;

import static com.cefisi.frank.business.QueryNames.VAT_ONE_BY_RATE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

/**
 * Represents a Value Added Tax (VAT) in time.
 * <p>
 * Class invariants:
 * <ul>
 * <li>rate and start date never {@code null}
 * <li>if end date is not {@code null} then the class ensures it's after start
 * date
 * </ul>
 */
@Entity
@Table(name = "t_vat")
@NamedQueries({
	@NamedQuery(name = VAT_ONE_BY_RATE, query = "select v from Vat v where v.rate = :rate") })
public class Vat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal rate;

    private LocalDate start;

    private LocalDate end;

    /**
     * Creates a new {@code Vat}.
     */
    protected Vat() {
	// Empty protected no-arg constructor for JPA
    }

    private Vat(Builder builder) {
	// Builder's private constructor
	id = builder.id;
	rate = builder.rate;
	start = builder.start;
    }

    /**
     * Returns the identifier for this {@code vat}.
     *
     * @return the identifier; {@code null} if not persisted
     */
    public Integer getId() {
	return id;
    }

    /**
     * Indicates whether or not this {@code vat} is persisted.
     * <p>
     * A {@code null} identifier indicates a non-persisted object.
     *
     * @return {@code true} if persisted; {@code false} otherwise
     */
    public final boolean isPersisted() {
	return null != id;
    }

    /**
     * Returns the rate for this {@code vat}.
     *
     * @return the rate
     */
    public BigDecimal getRate() {
	return rate;
    }

    /**
     * Returns the start date for this {@code vat}.
     *
     * @return the start date
     */
    public LocalDate getStart() {
	return start;
    }

    /**
     * Returns the end date for this {@code vat}.
     * <p>
     * A {@code null} return indicates an actual rate.
     *
     * @return the end date; may be {@code null}
     */
    public LocalDate getEnd() {
	return end;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code vat}.
     * <p>
     * Two {@code Vat} instances are considered equal if and only if their rates
     * and start dates are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code vat};
     *         {@code false} otherwise
     * @see BigDecimal#equals(Object)
     * @see LocalDate#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Vat)) {
	    return false;
	}
	Vat other = (Vat) obj;
	return rate.equals(other.rate) && start.equals(other.start);
    }

    // Lazily initialized cached hashcode
    @Transient
    private volatile int hashcode;

    /**
     * Returns a hashcode value for this {@code vat}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = hashcode;
	if (0 == hash) {
	    hashcode = Objects.hash(rate, start);
	}
	return hash;
    }

    /**
     * Returns the string representation for this {@code vat}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("{id=");
	sb.append(id);
	sb.append(", rate=");
	sb.append(rate);
	sb.append(", start=");
	sb.append(start);
	sb.append(", end=");
	sb.append(end);
	sb.append("}");
	return sb.toString();
    }

    /**
     * A builder to construct {@code Vat} objects.
     */
    public final static class Builder {

	private Integer id;

	private BigDecimal rate;

	private LocalDate start;

	private LocalDate end;

	/**
	 * Creates a new {@code Builder}.
	 */
	public Builder() {
	    // Empty constructor
	}

	/**
	 * Creates a new {@code Builder} in order to build a new {@code Vat} for
	 * update.
	 *
	 * @param original
	 *        an original {@code Vat} persisted instance
	 * @return a new {@code Builder} populated with given original
	 *         {@code Vat} instance
	 * @throws IllegalStateException
	 *         if {@code original} is not persisted
	 */
	public static Builder forUpdate(Vat original) {
	    Integer id = original.id;
	    if (null == id) {
		throw new IllegalStateException(
			"given original is not persisted");
	    }
	    Builder builder = new Builder();
	    builder.id = id;
	    builder.rate = original.rate;
	    builder.start = original.start;
	    builder.end = original.end;
	    return builder;
	}

	/**
	 * Assigns given rate to this {@code builder}.
	 *
	 * @param rate
	 *        a rate
	 * @return this {@code builder} for chaining
	 */
	public Builder setRate(BigDecimal rate) {
	    this.rate = rate;
	    return this;
	}

	/**
	 * Assigns given start date to this {@code builder}.
	 *
	 * @param date
	 *        a start date
	 * @return this {@code builder} for chaining
	 * @see #setEnd(LocalDate)
	 */
	public Builder setStart(LocalDate date) {
	    start = date;
	    return this;
	}

	/**
	 * Assigns given end date to this {@code builder}.
	 *
	 * @param date
	 *        an end date
	 * @return this {@code builder} for chaining
	 * @see #setStart(LocalDate)
	 */
	public Builder setEnd(LocalDate date) {
	    end = date;
	    return this;
	}

	/**
	 * Builds a new {@code Vat} object with provided rate and start date.
	 * <p>
	 * This implementation ensures class invariants.
	 *
	 * @return a new {@code Vat} object
	 * @throws NullPointerException
	 *         if either rate of start date is {@code null}
	 * @throws IllegalArgumentException
	 *         if the end date is not {@code null} and is not after provided
	 *         start date
	 */
	public Vat build() {
	    Objects.requireNonNull(rate);
	    Objects.requireNonNull(start);
	    if (null != end) {
		if (!end.isAfter(start)) {
		    throw new IllegalArgumentException("end date [" + end
			    + "] is not after start date [" + start + "]");
		}
	    }
	    return new Vat(this);
	}
    }
}