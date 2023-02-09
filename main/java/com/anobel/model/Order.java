package com.anobel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "orders")
    @ToString.Exclude
    private List<Client> clients;

    @Column(name = "cpu_price",nullable = false)
    private Double cpu_price;

    @Column(name = "gpu_price",nullable = false)
    private Double gpu_price;

    @Column(name = "storage_price",nullable = false)
    private Double storage_price;

    @Column(name = "motherboard_price",nullable = false)
    private Double motherboard_price;

    @Column(name = "ram_price",nullable = false)
    private Double ram_price;

    @Column(name = "discount",nullable = false)
    private Double discount;

    @Column(name = "total_price",nullable = false)
    private Double total_price;

    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy")
    private Date order_date;

    @Max(25)
    @Column(name = "order_status",nullable = false)
    private String order_status;

    @OneToOne(fetch = FetchType.LAZY)
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
