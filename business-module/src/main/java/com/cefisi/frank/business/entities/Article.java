package com.cefisi.frank.business.entities;

import java.math.BigDecimal;

/**
 * Represents an article.
 */
public class Article {

    private int id;

    private String code;

    private String description;

    private BigDecimal price;

    private Unit unit;

    private Vat vat;

    /**
     * Creates a new {@code Article}.
     */
    public Article() {
	/** Empty no-arg constructor */
    }

    /**
     * Returns the identifier for this {@code article}.
     *
     * @return the identifier
     */
    public int getId() {
	return id;
    }

    /**
     * Assigns given identifier to this {@code article}.
     *
     * @param id
     *        an identifier
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Returns the code for this {@code article}.
     *
     * @return the code
     */
    public String getCode() {
	return code;
    }

    /**
     * Assigns given code to this {@code article}.
     *
     * @param code
     *        a code
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * Returns the description for this {@code article}.
     *
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Assigns given description to this {@code article}.
     *
     * @param description
     *        a description
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Returns the price for this {@code article}.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
	return price;
    }

    /**
     * Assigns given price to this {@code article}.
     *
     * @param price
     *        a price
     */
    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    /**
     * Returns the unit for this {@code article}.
     *
     * @return the unit
     */
    public Unit getUnit() {
	return unit;
    }

    /**
     * Assigns given unit to this {@code article}.
     *
     * @param unit
     *        an unit
     */
    public void setUnit(Unit unit) {
	this.unit = unit;
    }

    /**
     * Returns the vat for this {@code article}.
     * <p>
     * A {@code null} return indicates an article not subject to <i>VAT</i>.
     *
     * @return the vat; may be {@code null}
     */
    public Vat getVat() {
	return vat;
    }

    /**
     * Assigns given vat to this {@code article}.
     *
     * @param vat
     *        a vat
     */
    public void setVat(Vat vat) {
	this.vat = vat;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code article}.
     * <p>
     * Two {@code Article} instances are considered equal if and only if their
     * codes are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code article};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Article)) {
	    return false;
	}
	Article other = (Article) obj;
	return code.equals(other.code);
    }

    /**
     * Returns a hashcode value for this {@code article}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = 17;
	hash += 31 * hash + code.hashCode();
	return hash;
    }

    /**
     * Returns the string representation for this {@code article}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("{id=");
	sb.append(id);
	sb.append(", code=");
	sb.append(code);
	sb.append("}");
	return sb.toString();
    }

    /**
     * The enumeration of article units.
     * <p>
     * Enumeration order is not meaningful.
     */
    public static enum Unit {
	/**
	 * Piece article unit.
	 */
	PIECE,
	/**
	 * Kilogram article unit.
	 */
	KILOGRAM,
	/**
	 * Month article unit.
	 */
	MONTH,
	/**
	 * Day article unit.
	 */
	DAY,
	/**
	 * Hour article unit.
	 */
	HOUR;
    }
}
