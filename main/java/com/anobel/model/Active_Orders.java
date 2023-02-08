package com.anobel.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "active_orders")
public class Active_Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany()
    private List<Order> orders;
}
