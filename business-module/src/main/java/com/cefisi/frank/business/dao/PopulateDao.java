package com.cefisi.frank.business.dao;

import static com.cefisi.frank.business.QueryNames.ADDRESS_ONE_BY_NAME;
import static com.cefisi.frank.business.QueryNames.ARTICLE_ONE_BY_CODE;
import static com.cefisi.frank.business.QueryNames.CONTACT_ONE_BY_LASTNAME;
import static com.cefisi.frank.business.QueryNames.VAT_ONE_BY_RATE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.cefisi.frank.business.entities.Address;
import com.cefisi.frank.business.entities.Article;
import com.cefisi.frank.business.entities.Contact;
import com.cefisi.frank.business.entities.Order;
import com.cefisi.frank.business.entities.Vat;

/**
 * A Data Access Object class to populate data.
 */
public final class PopulateDao extends EntityDao {

    /**
     * Creates a new {@code PopulateDao} with given factory.
     *
     * @param factory
     *        an entity manager factory
     * @throws NullPointerException
     *         if {@code factory} is {@code null}
     */
    public PopulateDao(EntityManagerFactory factory) {
	super(factory);
    }

    /**
     * Populates the contacts with {@code num} occurences.
     *
     * @param num
     *        number of contacts to populate
     * @return the number of populated contacts
     */
    public Long populateContacts(int num) {
	EntityManager em = start();
	Contact contact;
	Contact.Builder builder;
	long start = System.currentTimeMillis();
	for (int i = 0; i < num; i++) {
	    builder = new Contact.Builder();
	    String firstname = "FIRSTNAME_" + i;
	    builder.setFirstname(firstname);
	    String lastanme = "LASTNAME_" + i;
	    builder.setLastname(lastanme);
	    String account = firstname + "." + lastanme;
	    builder.setEmail(account + "@company.com");
	    contact = builder.build();
	    em.persist(contact);
	}
	end(em);
	long end = System.currentTimeMillis();
	System.out
		.println("##### contacts took " + (end - start) + " ms #####");
	return (Long) selectSingle("select count(*) from Contact");
    }

    /**
     * Populates the contacts with {@code num} addresses.
     *
     * @param num
     *        number of addresses to populate
     * @return the number of populated addresses
     */
    public Long populateAddresses(int num) {
	EntityManager em = start();
	Address address;
	Address.Builder builder;
	long start = System.currentTimeMillis();
	for (int i = 0; i < num; i++) {
	    builder = new Address.Builder();
	    builder.setName("COMPANY " + i);
	    builder.setStreet(i + 1 + " RUE GENERAL LECLERC");
	    builder.setZipCode("75000");
	    builder.setTown("PARIS");
	    address = builder.build();
	    em.persist(address);
	}
	end(em);
	long end = System.currentTimeMillis();
	System.out
		.println("##### addresses took " + (end - start) + " ms #####");
	return (Long) selectSingle("select count(*) from Address");
    }

    /**
     * Populates the contacts with {@code num} articles.
     *
     * @param num
     *        number of articles to populate
     * @return the number of populated articles
     */
    public Long populateArticles(int num) {
	EntityManager em = start();
	Article article;
	Article.Builder builder;
	long start = System.currentTimeMillis();
	for (int i = 0; i < num; i++) {
	    builder = new Article.Builder();
	    builder.setCode("CD" + i);
	    builder.setDescription("Description de l'article " + i);
	    BigDecimal price = BigDecimal.valueOf(55.58);
	    Article.Unit unit = Article.Unit.PIECE;
	    Query vatQuery = em.createNamedQuery(VAT_ONE_BY_RATE);
	    BigDecimal rateValue = BigDecimal.valueOf(20.0);
	    if (num / 2 <= i) {
		price = BigDecimal.valueOf(18.84);
		unit = Article.Unit.KILOGRAM;
		rateValue = BigDecimal.valueOf(5.5);
	    }
	    vatQuery.setParameter("rate", rateValue);
	    Vat vat = (Vat) vatQuery.getSingleResult();
	    builder.setPrice(price);
	    builder.setUnit(unit);
	    builder.setVat(vat);
	    article = builder.build();
	    em.persist(article);
	}
	end(em);
	long end = System.currentTimeMillis();
	System.out
		.println("##### articles took " + (end - start) + " ms #####");
	return (Long) selectSingle("select count(*) from Article");
    }

    /**
     * Populates the orders.
     *
     * @return the number of populated orders
     */
    public Long populateOrders() {
	EntityManager em = start();
	Order order;
	Order.Builder builder;
	int cmdNum = 1;
	Long maxContacts = (Long) em.createQuery("select count(c) from Contact c")
		.getSingleResult();
	Long maxArticles = (Long) em.createQuery("select count(a) from Article a")
		.getSingleResult();
	long start = System.currentTimeMillis();
	for (int i = 0; i < maxContacts.intValue(); i++) {
	    String lastname = "LASTNAME_" + i;
	    Contact contact = (Contact) em
		    .createNamedQuery(CONTACT_ONE_BY_LASTNAME)
		    .setParameter("lastname", lastname).getSingleResult();
	    String name = "COMPANY " + i;
	    Address address = (Address) em.createNamedQuery(ADDRESS_ONE_BY_NAME)
		    .setParameter("name", name).getSingleResult();
	    LocalDateTime firstOrder = LocalDateTime.now()
		    .minusDays(maxArticles);
	    for (int j = 0; j < maxArticles.intValue(); j++) {
		String code = "CD" + j;
		builder = new Order.Builder();
		Article article = (Article) em
			.createNamedQuery(ARTICLE_ONE_BY_CODE)
			.setParameter("code", code).getSingleResult();
		builder.addEntry(article, BigDecimal.ONE);
		builder.setReference("CMD#" + cmdNum++);
		builder.setAddress(address);
		builder.setContact(contact);
		LocalDateTime orderDate = firstOrder.plusDays(j);
		builder.setOrderDate(orderDate);
		LocalDate deliveryDate = orderDate.toLocalDate().plusDays(30);
		builder.setDeliveryDate(deliveryDate);
		order = builder.build();
		em.persist(order);
	    }
	    System.out.println("Counter=" + (i + 1) + "/" + maxContacts);
	}
	end(em);
	long end = System.currentTimeMillis();
	System.out.println("##### orders took " + (end - start) + " ms #####");
	return (Long) selectSingle("select count(*) from Order");
    }
}
