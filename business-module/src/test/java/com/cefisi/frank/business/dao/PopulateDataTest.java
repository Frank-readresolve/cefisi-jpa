package com.cefisi.frank.business.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Class to load data.
 */
public class PopulateDataTest extends DaoBaseTest {

    private final static int MAX_CONTACTS_ADDRESSES = 100;

    private final static int MAX_ARTICLES = 40;

    private final PopulateDao dao = new PopulateDao(EMF);

    /**
     * Data loading test.
     */
    @Test
    public void shouldLoadData() {
	Long result = dao.populateContacts(MAX_CONTACTS_ADDRESSES);
	assertTrue("populating contacts failed, result=" + result,
		Long.valueOf(MAX_CONTACTS_ADDRESSES).equals(result));
	result = dao.populateAddresses(MAX_CONTACTS_ADDRESSES);
	assertTrue("populating addresses failed, result=" + result,
		Long.valueOf(MAX_CONTACTS_ADDRESSES).equals(result));
	result = dao.populateArticles(MAX_ARTICLES);
	assertTrue("populating articles failed, result=" + result,
		Long.valueOf(MAX_ARTICLES).equals(result));
	result = dao.populateOrders();
	assertTrue("populating orders failed, result=" + result, Long
		.valueOf(MAX_CONTACTS_ADDRESSES * MAX_ARTICLES).equals(result));
    }
}
