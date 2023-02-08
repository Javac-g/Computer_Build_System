package com.anobel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import java.util.*;


@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message = "must be valid email adress")
    @Column(name = "email",nullable = false)
    private String login;

    @Pattern(regexp = "[A-Za-z\\d]{6,}", message="Must be 6 symbols long")
    @Pattern(regexp = ".*\\d.*", message = "Must have at least one number")
    @Pattern(regexp = ".*[A-Z].*", message="Must have at east one uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message="Must have at east one lowercase letter")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name",nullable = false)
    private String secondName;

    @Column(name = "discount")
    private double discount;
    @Column(name = "orders_number")
    private int orders_number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinTable(name = "Order", joinColumns = @JoinColumn(name = ""))
    private List<Order> myOders;


}
