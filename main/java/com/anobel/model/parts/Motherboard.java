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
@Table(name = "motherboard_list")
@NoArgsConstructor
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;
   

	 @Column(name = "socket_type")
    private String socketType;

    @Column(name = "model")
    private String model;

    @Column(name = "chipset")
    private String chipset;

    @Column(name = "ram_type")
    private String ramType;

    @Column(name = "ram_speed")
    private Integer ramSpeed;

    @Column(name = "tdp")
    private Integer tdp;

    @Column(name = "bios_version")
    private Double biosVersion;

    @Column(name = "brand")
    private String brand;

    @Column(name = "ram_capacity")
    private Integer ramCapacity;

    @Column(name = "pcie_type")
    private String pcieType;

    @Column(name = "storage_ports")
    private String storagePorts;

    @Column(name = "wattage")
    private Integer wattage;

    @Column(name = "form_factor")
    private String formFactor;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Motherboard_price_history motherboard_price_history;



}
