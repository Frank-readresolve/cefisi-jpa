/**
 * This package contains the business module entities.
 * <p>
 * The access strategy is field by default.
 * <p>
 * All the entities implements the builder design pattern ease object building
 * and immutability. Each class provides an inner class builder named
 * {@code <ENTITY_NAME>} suffixed by {@code Builder}. See <i>Effective Java</i>
 * by J. BLOCH for details on the pattern.
 * <p>
 * Regarding class invariants, please note that otherwise stated accessors never
 * return {@code null} and always return a consistent state. Also, implemented
 * builders guarantee the class invariants as entity's mutators do. Builders
 * build entity with a {@code null} identifier.
 */
package com.cefisi.frank.business.entities;