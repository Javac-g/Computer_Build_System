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
@Table(name = "cpu_list")
@NoArgsConstructor
public class Cpu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;


    @Column(name = "tdp")
    private Integer tdp;

    @Column(name = "core_count")
    private Integer coreCount;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "performance_core_clock")
    private Integer performanceCoreClock;

    @Column(name = "performance_boost_clock")
    private Integer performanceBoostClock;

    @Column(name = "integrated_graphics")
    private String integratedGraphics;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "socket_type")
    private String socketType;

    @Column(name = "threads")
    private Integer threads;

    @Column(name = "wattage")
    private Integer wattage;

    @Column(name = "cpu_speed")
    private Double cpuSpeed;

    @Column(name = "brand")
    private String brand;

    @Column(name = "cpu_model")
    private String cpuModel;

    @Column(name = "cpu_processor_type")
    private String cpuProcessorType;

    @Column(name = "chipset")
    private String chipset;

    @Column(name = "ram_type")
    private String ramType;

    @Column(name = "ram_speed")
    private Integer ramSpeed;

    @Column(name = "bios_version")
    private Double biosVersion;

    @Column(name = "supported_gpu_model")
    private String supportedGpuModel;

   
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Cpu_price_history cpu_price_history;
}
