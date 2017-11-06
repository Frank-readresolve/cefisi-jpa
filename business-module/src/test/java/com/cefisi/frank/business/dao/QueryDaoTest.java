package com.cefisi.frank.business.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

/**
 * Class test over {@code Vat} class.
 */
public class QueryDaoTest extends DaoBaseTest {

    private final EntityDao dao = new EntityDao(EMF);

    /**
     * Verifies a JPQL query returns a single non {@code null} result.
     */
    @Test
    public void testSingleResultQuery() {
	String jpql = "";
	Object result = dao.selectSingle(jpql);
	assertNotNull(result);
	System.out.println("result=" + result);
    }

    /**
     * Verifies a JPQL query returns a multiple result list.
     */
    @Test
    public void testMultipleResultQuery() {
	String jpql = "";
	List<?> result = dao.selectMultiple(jpql);
	assertNotNull(result);
	System.out.println("result=" + result);
    }
}