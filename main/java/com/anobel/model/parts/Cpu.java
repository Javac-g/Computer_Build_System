package com.anobel.model.parts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CPU_list")
@NoArgsConstructor
public class Cpu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "tdp",nullable = false)
    private Integer tdp;

    @Column(name = "core_count",nullable = false)
    private Integer core_count;
    @Column(name = "socket", nullable = false)
    private String socket;

    @Max(25)
    @Column(name = "manufacturer",nullable = false,insertable=false, updatable=false)
    private String manufacturer;

    @Column(name = "performance_core_clock",nullable = false)
    private Integer performance_core_clock;

    @Column(name = "performance_boost_clock",nullable = false)
    private Integer performance_boost_clock;

    @Max(25)
    @Column(name = "manufacturer",nullable = false)
    private String integrated_graphics;
}
