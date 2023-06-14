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
@Table(name = "Ram_list")
@NoArgsConstructor
public class Ram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

     @Column(name = "speed")
    private Integer speed;

    @Column(name = "modules")
    private String modules;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "ram_type")
    private String ramType;

    @Column(name = "wattage")
    private Integer wattage;

    @Column(name = "cas_latency")
    private Integer casLatency;

    @Column(name = "voltage")
    private Double voltage;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Ram_price_history ram_price_history;
}
