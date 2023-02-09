package com.anobel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Purpose")
public class Purpose {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "purpose_name",nullable = false)
    private String purpose_name;
}
