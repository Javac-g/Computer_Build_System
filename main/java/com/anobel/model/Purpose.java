package com.anobel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Purpose")
public class Purpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purpose_name",nullable = false)
    private String purpose_name;
}
