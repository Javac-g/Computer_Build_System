package com.anobel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "active_orders")
public class Active_Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne()
    @JoinColumn(name = "client_id")
    private Client client;

}
