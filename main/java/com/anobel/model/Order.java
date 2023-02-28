package com.anobel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "orders")
    @ToString.Exclude
    private List<Client> clients;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_price")
    private Double cpu_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_price")
    private Double gpu_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_price")
    private Double storage_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_price")
    private Double motherboard_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_price")
    private Double ram_price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "total_price",nullable = false)
    private Double total_price;

    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy")
    private LocalDateTime order_date;

    @Max(25)
    @Column(name = "order_status",nullable = false)
    private String order_status;

    @OneToOne()
    @JoinColumn(name = "assembly_id")
    private Assembly assembly;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getId() != null && getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
