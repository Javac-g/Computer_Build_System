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
@Table(name = "Storage_list")
@NoArgsConstructor
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "capacity",nullable = false)
    private Integer capacity;

    @Max(5)
    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "interface",nullable = false)
    private String s_interface;

    @Max(25)
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private Storage_price_history storage_price_history;
}
