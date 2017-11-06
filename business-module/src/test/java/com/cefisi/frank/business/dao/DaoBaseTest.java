package com.cefisi.frank.business.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Class handling {@code EntityManagerFactory} creation and realease to help in
 * testing data access classes.
 */
public class DaoBaseTest {

    protected static EntityManagerFactory EMF;

    /**
     * Creates the entity manager factory.
     *
     * @throws FileNotFoundException
     * @throws SQLException
     */
    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {
	
	EMF = Persistence.createEntityManagerFactory("CefisiServices");
    }

    /**
     * Returns the entity manager factory.
     *
     * @return the entity manager factory
     */
    protected final static EntityManagerFactory getFactory() {
	return EMF;
    }

    /**
     * Closes the entity manager factory.
     */
    @AfterClass
    public static void tearDown() {

	EMF.close();
    }
}
