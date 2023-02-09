package com.anobel.model.parts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Storage_list")
@NoArgsConstructor
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacity",nullable = false)
    private Integer capacity;

    @Max(5)
    @Column(name = "type",nullable = false)
    private String type;

    @Max(25)
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;
}
