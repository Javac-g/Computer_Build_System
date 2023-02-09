package com.anobel.model.parts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "GPU_list")
@NoArgsConstructor
public class Gpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "memory",nullable = false)
    private Integer memory;

    @Column(name = "length",nullable = false)
    private Integer length;

    @Max(25)
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;

    @Max(15)
    @Column(name = "chipset",nullable = false)
    private String chipset;

    @Column(name = "core_clock",nullable = false)
    private Integer core_clock;

    @Column(name = "boost_clock",nullable = false)
    private Integer boost_clock;
}
