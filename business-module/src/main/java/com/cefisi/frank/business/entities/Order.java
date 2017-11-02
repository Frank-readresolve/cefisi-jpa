package com.cefisi.frank.business.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class Order {

    private int id;

    private String number;

    private Set<OrderEntry> entries;

    private LocalDateTime orderDate;

    private LocalDate deliveryDate;

    private Contact contact;

    private Address address;

    /**
     * Creates a new {@code Order}.
     */
    public Order() {
	/** Empty no-arg constructor */
    }
}
