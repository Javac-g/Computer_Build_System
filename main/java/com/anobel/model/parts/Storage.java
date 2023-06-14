package com.anobel.model.parts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.anobel.model.price.*;

@Getter
@Setter
@Entity
@Table(name = "storage_list")
@NoArgsConstructor
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "type")
    private String type;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "interface")
    private String interfaceType;

    @Column(name = "cache_size")
    private Integer cacheSize;

    @Column(name = "wattage")
    private Integer wattage;

    @Column(name = "readspeed")
    private Double readSpeed;

    @Column(name = "writespeed")
    private Double writeSpeed;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Storage_price_history storage_price_history;
}
