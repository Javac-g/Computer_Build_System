package com.anobel.model.parts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ram_list")
@NoArgsConstructor
public class Ram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Max(25)
    @Column(name = "speed",nullable = false)
    private String speed;
    @Max(25)
    @Column(name = "ram_type",nullable = false)
    private String ram_type;
    @Max(25)
    @Column(name = "modules",nullable = false)
    private String modules;

    @Max(25)
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;
}
