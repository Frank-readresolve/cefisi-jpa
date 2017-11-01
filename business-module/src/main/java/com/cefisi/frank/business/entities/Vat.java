package com.cefisi.frank.business.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

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

    private Vat(BigDecimal rate, LocalDate start) {
	// Builder's private constructor
	this.rate = rate;
	this.start = start;
    }

    /**
     * Returns the identifier for this {@code vat}.
     *
     * @return the identifier
     */
    public Integer getId() {
	return id;
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
     * Assigns given end date to this {@code vat}.
     *
     * @param date
     *        an end date
     * @throws NullPointerException
     *         if {@code date} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code date} is not after {@linkplain #getStart() start date}
     */
    public void setEnd(LocalDate date) {
	if (!date.isAfter(start)) {
	    throw new IllegalArgumentException("date [" + date
		    + "] is not after start date [" + start + "]");
	}
	end = date;
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
	    hash = 17;
	    hash += 31 * hash + rate.hashCode();
	    hash += 31 * hash + start.hashCode();
	    hashcode = hash;
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
    public final static class VatBuilder {

	private BigDecimal rate;

	private LocalDate start;

	/**
	 * Creates a new {@code VatBuilder}.
	 */
	public VatBuilder() {
	    // Empty constructor
	}

	/**
	 * Assigns given rate to this {@code builder}.
	 *
	 * @param rate
	 *        a rate
	 * @return this {@code builder} for chaining
	 */
	public VatBuilder setRate(BigDecimal rate) {
	    this.rate = rate;
	    return this;
	}

	/**
	 * Assigns given start date to this {@code builder}.
	 *
	 * @param date
	 *        a start date
	 * @return this {@code builder} for chaining
	 */
	public VatBuilder setStart(LocalDate date) {
	    start = date;
	    return this;
	}

	/**
	 * Builds a new {@code Vat} object with given rate and start date.
	 * <p>
	 * This implementation ensures class invariants. Returned {@code Vat}
	 * instance has a {@code null} end date.
	 *
	 * @return a new {@code Vat} object
	 * @throws NullPointerException
	 *         if any of the argument is {@code null}
	 */
	public Vat build() {
	    Objects.requireNonNull(rate);
	    Objects.requireNonNull(start);
	    return new Vat(rate, start);
	}
    }
}