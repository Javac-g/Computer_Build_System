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
@Table(name = "gpu_list")
@NoArgsConstructor
public class Gpu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "memory_size")
    private Integer memorySize;

    @Column(name = "length")
    private Integer length;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "chipset")
    private String chipset;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "tdp")
    private Integer tdp;

    @Column(name = "pcie_type")
    private String pcieType;

    @Column(name = "wattage")
    private Integer wattage;

    @Column(name = "model")
    private String model;

    @Column(name = "gpu_pcie_slot")
    private String gpuPcieSlot;

    @Column(name = "gpu_form_factor")
    private String gpuFormFactor;

	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Gpu_price_history gpu_price_history;
}
