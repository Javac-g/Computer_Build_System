package com.anobel.model.parts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Motherboard_list")
@NoArgsConstructor
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "memory_slots",nullable = false)
    private Integer memory_slots;

    @Max(15)
    @Column(name = "socket/cpu",nullable = false)
    private String socket_cpu;

    @Max(25)
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;

    @Max(25)
    @Column(name = "form_factor",nullable = false)
    private String form_factor;

    @Column(name = "memory_max",nullable = false)
    private Integer memory_max;


}
