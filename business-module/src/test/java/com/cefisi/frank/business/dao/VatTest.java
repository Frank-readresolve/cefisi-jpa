package com.cefisi.frank.business.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.PersistenceException;

import org.junit.Test;

import com.cefisi.frank.business.entities.Vat;
import com.cefisi.frank.business.entities.Vat.VatBuilder;

/**
 * Class test over {@code Vat} class.
 */
public class VatTest extends DaoBaseTest {

    private final EntityDao dao = new EntityDao(EMF);

    /**
     * Verifies that a new {@code Vat} instance is persisted.
     */
    @Test
    public void shouldPersistVat() {
	BigDecimal rate = BigDecimal.valueOf(20.0);
	LocalDate start = LocalDate.now();
	Vat vat = new VatBuilder().setRate(rate).setStart(start).build();
	dao.persist(vat);
	assertNotNull(vat.getId()); // Should be 5
    }

    /**
     * Verifies that an existing persisted {@code Vat} cannot be persisted.
     */
    @Test(expected = PersistenceException.class)
    public void shouldNotPersistVat() {
	Vat vat = dao.find(Vat.class, 1);
	dao.persist(vat);
    }

    /**
     * Verifies that the end date of a persisted {@code Vat} is updated.
     */
    @Test
    public void shouldUpdateVat() {
	Vat vat = dao.find(Vat.class, 3);
	vat.setEnd(LocalDate.now());
	dao.update(vat);
	Vat updated = dao.find(Vat.class, 3);
	assertEquals(vat.getEnd(), updated.getEnd());
    }

    /**
     * Verifies that the end date of a persisted {@code Vat} is updated.
     */
    @Test
    public void shouldRemoveVat() {
	Vat vat = dao.find(Vat.class, 4);
	assertNotNull(vat);
	dao.remove(vat);
	Vat removed = dao.find(Vat.class, 4);
	assertNull(removed);
    }
}