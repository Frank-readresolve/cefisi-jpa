package com.cefisi.frank.business.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;

import com.cefisi.frank.utils.MathUtils;

/**
 * Represents an article.
 * <p>
 * Class invariants:
 * <ul>
 * <li>order, quantity, article never {@code null}
 * <li>quantity always positive
 * </ul>
 */
@Entity
@Table(name = "t_orderentry")
public class OrderEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_order_id"))
    private Order order;

    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_id"))
    private Article article;

    /**
     * Creates a new {@code OrderEntry}.
     */
    protected OrderEntry() {
	// Empty protected no-arg constructor for JPA
    }

    private OrderEntry(Builder builder) {
	// Builder's private constructor
	id = builder.id;
	order = builder.order;
	quantity = builder.quantity;
	article = builder.article;
    }

    /**
     * Returns the identifier for this {@code entry}.
     *
     * @return the identifier; {@code null} if not persisted
     */
    public Integer getId() {
	return id;
    }

    /**
     * Indicates whether or not this {@code entry} is persisted.
     * <p>
     * A {@code null} identifier indicates a non-persisted object.
     *
     * @return {@code true} if persisted; {@code false} otherwise
     */
    public final boolean isPersisted() {
	return null != id;
    }

    /**
     * Returns the order for this {@code entry}.
     *
     * @return the order
     */
    public Order getOrder() {
	return order;
    }

    /**
     * Returns the quantity for this {@code entry}.
     *
     * @return the quantity
     */
    public BigDecimal getQuantity() {
	return quantity;
    }

    /**
     * Returns the article for this {@code entry}.
     *
     * @return the article
     */
    public Article getArticle() {
	return article;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code entry}.
     * <p>
     * Two {@code OrderEntry} instances are considered equal if and only if
     * their orders and articles are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code entry};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof OrderEntry)) {
	    return false;
	}
	OrderEntry other = (OrderEntry) obj;
	return order.equals(other.order) && article.equals(other.article);
    }

    // Lazily initialized cached hashcode
    @Transient
    private volatile int hashcode;

    /**
     * Returns a hashcode value for this {@code entry}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = hashcode;
	if (0 == hash) {
	    hashcode = Objects.hash(order, article);
	}
	return hash;
    }

    /**
     * Returns the string representation for this {@code entry}.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("{id=");
	sb.append(id);
	sb.append(", ownerOrder.ref=");
	sb.append(order.getReference());
	sb.append(", article.code=");
	sb.append(article.getCode());
	sb.append(", quantity=");
	sb.append(quantity);
	sb.append("}");
	return sb.toString();
    }

    /**
     * A builder to construct {@code OrderEntry} objects.
     */
    final static class Builder {

	private Integer id;

	private Order order;

	private BigDecimal quantity;

	private Article article;

	/**
	 * Creates a new {@code Builder}.
	 */
	Builder() {
	    // Empty constructor
	}

	/**
	 * Creates a new {@code Builder} in order to build a new
	 * {@code OrderEntry} for update.
	 *
	 * @param original
	 *        an original {@code OrderEntry} persisted instance
	 * @return a new {@code Builder} populated with given original
	 *         {@code OrderEntry} instance
	 * @throws IllegalStateException
	 *         if {@code original} is not persisted
	 */
	static Builder forUpdate(OrderEntry original) {
	    Integer id = original.id;
	    if (null == id) {
		throw new IllegalStateException(
			"given original is not persisted");
	    }
	    Builder builder = new Builder();
	    builder.id = id;
	    builder.quantity = original.quantity;
	    builder.article = original.article;
	    return builder;
	}

	/**
	 * Assigns given order to this {@code builder}.
	 *
	 * @param order
	 *        an order
	 * @return this {@code builder} for chaining
	 */
	Builder setOrder(Order order) {
	    this.order = order;
	    return this;
	}

	/**
	 * Assigns given quantity to this {@code builder}.
	 *
	 * @param quantity
	 *        a quantity
	 * @return this {@code builder} for chaining
	 */
	Builder setQuantity(BigDecimal quantity) {
	    this.quantity = quantity;
	    return this;
	}

	/**
	 * Assigns given article to this {@code builder}.
	 *
	 * @param article
	 *        an article
	 * @return this {@code builder} for chaining
	 */
	Builder setArticle(Article article) {
	    this.article = article;
	    return this;
	}

	/**
	 * Builds a new {@code OrderEntry} object with provided order, article
	 * and quantity.
	 * <p>
	 * This implementation ensures class invariants.
	 *
	 * @return a new {@code OrderEntry} object
	 * @throws NullPointerException
	 *         if any of the provided argument is {@code null}
	 * @throws IllegalArgumentException
	 *         if the quantity is not positive
	 */
	OrderEntry build() {
	    Objects.requireNonNull(order);
	    Objects.requireNonNull(article);
	    if (!MathUtils.isPositive(quantity)) {
		throw new IllegalArgumentException(
			"quantity [" + quantity + "] is not positive");
	    }
	    return new OrderEntry(this);
	}
    }
}
