package com.anobel.model.price;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "storage_price_history")

public class Storage_price_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "start_price")
    private float start_price;
    @Column(name = "last_price")
    private float last_price;
    @Column(name = "start_price_date")
    private LocalDateTime start_price_date;
    @Column(name = "last_price_date")
    private LocalDateTime last_price_date;
}
