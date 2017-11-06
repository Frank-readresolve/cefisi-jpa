package com.cefisi.frank.business;

/**
 * Defines constants to help dealing with named queries names.
 */
public final class QueryNames {

    private QueryNames() {
	// Ensures non-instantiability
    }

    // Vat entity query names
    /**
     * Query name to find one {@code Vat} given its rate.
     * <p>
     * Parameters names:
     * <ul>
     * <li>{@code rate}
     * </ul>
     */
    public final static String VAT_ONE_BY_RATE = "Vat.findOneByRate";

    // Article entity query names
    /**
     * Query name to find one {@code Article} given its code.
     * <p>
     * Parameters names:
     * <ul>
     * <li>{@code code}
     * </ul>
     */
    public final static String ARTICLE_ONE_BY_CODE = "Article.findOneByCode";

    // Contact entity query names
    /**
     * Query name to find one {@code Contact} given its lastname.
     * <p>
     * Parameters names:
     * <ul>
     * <li>{@code lastname}
     * </ul>
     */
    public final static String CONTACT_ONE_BY_LASTNAME = "Contact.findOneByLastname";

    // Address entity query names
    /**
     * Query name to find one {@code Address} given its name.
     * <p>
     * Parameters names:
     * <ul>
     * <li>{@code name}
     * </ul>
     */
    public final static String ADDRESS_ONE_BY_NAME = "Address.findOneByName";
}
