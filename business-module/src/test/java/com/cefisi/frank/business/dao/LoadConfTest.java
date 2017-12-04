package com.cefisi.frank.business.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Class to test persistence configuration.
 */
public class LoadConfTest extends DaoBaseTest {

    @SuppressWarnings("unused")
    private final EntityDao dao = new EntityDao(EMF);

    /**
     * To load configuration.
     */
    @Test
    public void shouldLoadCOnf() {
	assertNotNull("load conf");
    }
}