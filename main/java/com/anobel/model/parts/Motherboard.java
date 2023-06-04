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
@Table(name = "Motherboard_list")
@NoArgsConstructor
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "ram_slots",nullable = false)
    private Integer ram_slots;

    @Column(name = "ram_max",nullable = false)
    private String memory_max;

    @Column(name = "ram_type",nullable = false)
    private String ram_type;
    @Column(name = "ram_speed",nullable = false)
    private String ram_speed;
    @Column(name = "gpu_type",nullable = false)
    private String gpu_type;

    @Column(name = "storage_type",nullable = false)
    private String storage_type;

   
    @Column(name = "socket_cpu",nullable = false)
    private String socket_cpu;
	@Column(name = "connector_interface",nullable = false)
    private String connector_interface;
  
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;

   
    @Column(name = "form_factor",nullable = false)
    private String form_factor;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Motherboard_price_history motherboard_price_history;



}
