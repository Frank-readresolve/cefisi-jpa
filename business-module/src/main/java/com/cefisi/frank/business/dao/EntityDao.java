package com.cefisi.frank.business.dao;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * A base Data Access Object class to deal with common <b>atomic</b> operations
 * on a single {@code EntityManagerFactory} in a non-JTA context. Transactional
 * operations fully manage the transactional context from beginning to
 * rollbacking when necessary.
 * <p>
 * Please note that the class is not responsible for closing the entity manager
 * factory, this should be done by a client class like an initialization servlet
 * or a context listener.
 * <p>
 * If the provided entity manager factory is closed or a JTA context factory,
 * any subsequent access to any of the method will result in an
 * {@code IllegalStateException}.
 */
public class EntityDao {

    /**
     * The underliying entity manager factory.
     */
    private EntityManagerFactory factory;

    /**
     * Creates a new {@code EntityDao} with given factory.
     *
     * @param factory
     *        an entity manager factory
     * @throws NullPointerException
     *         if {@code factory} is {@code null}
     */
    public EntityDao(EntityManagerFactory factory) {
	Objects.requireNonNull(factory);
	this.factory = factory;
    }

    /**
     * Returns the underliying entity manager factory.
     *
     * @return the entity manager factory
     */
    public final EntityManagerFactory getFactory() {
	return factory;
    }

    /**
     * Persists an entity.
     *
     * @param entity
     *        an entity instance to persist
     */
    public final void persist(Object entity) {
	EntityManager em = null;
	try {
	    em = start();
	    em.persist(entity);
	} finally {
	    end(em);
	}
    }

    /**
     * Updates an entity.
     *
     * @param entity
     *        an entity instance to update
     */
    public final void update(Object entity) {
	EntityManager em = null;
	try {
	    em = start();
	    em.merge(entity);
	} finally {
	    end(em);
	}
    }

    /**
     * Updates an entity; either managed or detached.
     *
     * @param entity
     *        an entity instance to update
     */
    public final void remove(Object entity) {
	EntityManager em = null;
	try {
	    em = start();
	    em.remove(em.contains(entity) ? entity : em.merge(entity));
	} finally {
	    end(em);
	}
    }

    /**
     * Return a persisted entity with given identifier.
     *
     * @param <T>
     * @param type
     *        the type of entity
     * @param id
     *        the identifier
     * @return a persisted entity with given identifier; {@code null} if does
     *         not exist
     */
    public final <T> T find(Class<T> type, Object id) {
	EntityManager em = startSimple();
	T result = em.find(type, id);
	end(em);
	return result;
    }

    /**
     * Starts a unit of work beginning a transaction.
     *
     * @return a new entity manager
     * @throws IllegalStateException
     *         if a transaction is already active
     */
    protected final EntityManager start() {
	EntityManager em = factory.createEntityManager();
	em.getTransaction().begin();
	return em;
    }

    /**
     * Starts a unit of work without beginning a transaction.
     *
     * @return a new entity manager
     */
    protected final EntityManager startSimple() {
	return factory.createEntityManager();
    }

    protected final void end(EntityManager em) {
	try {
	    EntityTransaction tx = em.getTransaction();
	    if (tx.isActive()) {
		try {
		    tx.commit();
		} catch (Exception ex) {
		    tx.rollback();
		}
	    }
	} finally {
	    em.clear();
	    em.close();
	}
    }
}
