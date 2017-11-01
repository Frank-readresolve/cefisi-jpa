package com.cefisi.frank.business.entities;

import java.math.BigDecimal;

/**
 * Represents an order entry.
 */
public class OrderEntry {

    private int id;

    private Order order;

    private Article article;

    private BigDecimal quantity;

    /**
     * Creates a new {@code OrderEntry}.
     */
    public OrderEntry() {
	/** Empty no-arg constructor */
    }

    /**
     * Returns the identifier for this {@code entry}.
     *
     * @return the identifier
     */
    public int getId() {
	return id;
    }

    /**
     * Assigns given identifier to this {@code entry}.
     *
     * @param id
     *        an identifier
     */
    public void setId(int id) {
	this.id = id;
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
     * Assigns given order to this {@code entry}.
     *
     * @param order
     *        an order
     */
    public void setOrder(Order order) {
	this.order = order;
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
     * Assigns given article to this {@code entry}.
     *
     * @param article
     *        an article
     */
    public void setArticle(Article article) {
	this.article = article;
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
     * Assigns given quantity to this {@code entry}.
     *
     * @param quantity
     *        a quantity
     */
    public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
    }

    /**
     * Indicates whether or not given object is "equal to" this {@code entry}.
     * <p>
     * Two {@code OrderEntry} instances are considered equal if and only if their
     * orders and articles are equal.
     *
     * @return {@code true} if {@code obj} is "equal to" this {@code entry};
     *         {@code false} otherwise
     * @see Order#equals(Object)
     * @see Article#equals(Object)
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

    /**
     * Returns a hashcode value for this {@code entry}.
     * <p>
     * This implementation is consistent with {@code equals}.
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
	int hash = 17;
	hash += 31 * hash + order.hashCode();
	hash += 31 * hash + article.hashCode();
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
	sb.append(", order=");
	sb.append(order);
	sb.append(", article=");
	sb.append(article);
	sb.append("}");
	return sb.toString();
    }
}
