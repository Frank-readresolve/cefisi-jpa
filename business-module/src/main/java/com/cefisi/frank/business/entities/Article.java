package com.cefisi.frank.business.entities;

import static com.cefisi.frank.business.QueryNames.ARTICLE_ONE_BY_CODE;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;

/**
 * Represents an article.
 * <p>
 * Class invariants:
 * <ul>
 * <li>code, description, price and unit never {@code null}
 * </ul>
 */
@Entity
@Table(name = "t_article")
@NamedQueries({
	@NamedQuery(name = ARTICLE_ONE_BY_CODE, query = "select a from Article a where a.code = :code") })
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_vat_id"))
    private Vat vat;

    /**
     * Creates a new {@code Article}.
     */
    protected Article() {
	// Empty protected no-arg constructor for JPA
    }

    private Article(Builder builder) {
	// Builder's private constructor
	id = builder.id;
	code = builder.code;
	description = builder.description;
	price = builder.price;
	unit = builder.unit;
	vat = builder.vat;
    }

    /**
     * Returns the identifier for this {@code article}.
     *
     * @return the identifier; {@code null} if not persisted
     */
    public Integer getId() {
	return id;
    }

    /**
     * Indicates whether or not this {@code article} is persisted.
     * <p>
     * A {@code null} identifier indicates a non-persisted object.
     *
     * @return {@code true} if persisted; {@code false} otherwise
     */
    public final boolean isPersisted() {
	return null != id;
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
     * Returns the description for this {@code article}.
     *
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Returns the price without tax for this {@code article}.
     *
     * @return the price without tax
     */
    public BigDecimal getPrice() {
	return price;
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

    // Lazily initialized cached hashcode
    @Transient
    private volatile int hashcode;

    /**
     * Returns a hashcode value for this {@code article}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = hashcode;
	if (0 == hash) {
	    hashcode = Objects.hash(code);
	}
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
	sb.append(", desc=");
	sb.append(description);
	sb.append(", price=");
	sb.append(price);
	sb.append(", unit=");
	sb.append(unit);
	sb.append(", vat=");
	sb.append(vat);
	sb.append("}");
	return sb.toString();
    }

    /**
     * A builder to construct {@code Article} objects.
     */
    public final static class Builder {

	private Integer id;

	private String code;

	private String description;

	private BigDecimal price;

	private Unit unit;

	private Vat vat;

	/**
	 * Creates a new {@code Builder}.
	 */
	public Builder() {
	    // Empty constructor
	}

	/**
	 * Creates a new {@code Builder} in order to build a new {@code Article}
	 * for update.
	 *
	 * @param original
	 *        an original {@code Article} persisted instance
	 * @return a new {@code Builder} populated with given original
	 *         {@code Article} instance
	 * @throws IllegalStateException
	 *         if {@code original} is not persisted
	 */
	public static Builder forUpdate(Article original) {
	    Integer id = original.id;
	    if (null == id) {
		throw new IllegalStateException(
			"given original is not persisted");
	    }
	    Builder builder = new Builder();
	    builder.id = id;
	    builder.code = original.code;
	    builder.description = original.description;
	    builder.price = original.price;
	    builder.unit = original.unit;
	    builder.vat = original.vat;
	    return builder;
	}

	/**
	 * Assigns given code to this {@code builder}.
	 *
	 * @param code
	 *        a code
	 * @return this {@code builder} for chaining
	 */
	public Builder setCode(String code) {
	    this.code = code;
	    return this;
	}

	/**
	 * Assigns given description to this {@code builder}.
	 *
	 * @param description
	 *        a description
	 * @return this {@code builder} for chaining
	 */
	public Builder setDescription(String description) {
	    this.description = description;
	    return this;
	}

	/**
	 * Assigns given price without tax to this {@code builder}.
	 *
	 * @param price
	 *        a price without tax
	 * @return this {@code builder} for chaining
	 */
	public Builder setPrice(BigDecimal price) {
	    this.price = price;
	    return this;
	}

	/**
	 * Assigns given unit to this {@code builder}.
	 *
	 * @param unit
	 *        an unit
	 * @return this {@code builder} for chaining
	 */
	public Builder setUnit(Unit unit) {
	    this.unit = unit;
	    return this;
	}

	/**
	 * Assigns given vat to this {@code builder}.
	 *
	 * @param vat
	 *        a VAT
	 * @return this {@code builder} for chaining
	 */
	public Builder setVat(Vat vat) {
	    this.vat = vat;
	    return this;
	}

	/**
	 * Builds a new {@code Article} object with provided firstname, lastname
	 * and email.
	 * <p>
	 * This implementation ensures class invariants.
	 *
	 * @return a new {@code Article} object
	 * @throws NullPointerException
	 *         if any of the provided argument but the VAT is {@code null}
	 */
	public Article build() {
	    Objects.requireNonNull(code);
	    Objects.requireNonNull(description);
	    Objects.requireNonNull(price);
	    Objects.requireNonNull(unit);
	    return new Article(this);
	}
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
