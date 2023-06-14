package com.anobel.model.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "computer_case_list")
@NoArgsConstructor
public class ComputerCase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;


    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "model",nullable = false)
    private String model;

    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "max_gpu_length",nullable = false)
    private Integer max_gpu_length;

    @Column(name = "max_cpu_cooler_height",nullable = false)
    private Integer max_cpu_cooler_height;

    @Column(name = "max_psu_length",nullable = false)
    private Integer max_psu_length;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private ComputerCase_price_history computerCase_price_history;


}
