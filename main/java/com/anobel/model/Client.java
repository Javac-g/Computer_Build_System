package com.anobel.model;

import jakarta.persistence.*;




@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "email",nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
}
