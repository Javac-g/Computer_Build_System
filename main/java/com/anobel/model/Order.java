package com.anobel.model;

import com.anobel.model.price.*;
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
    @JoinColumn(name = "cpu_price_id")
    private Cpu_price_history cpu_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_price_id")
    private Gpu_price_history gpu_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_price_id")
    private Storage_price_history storage_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motherboard_price_id")
    private Motherboard_price_history motherboard_price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ram_price_id")
    private Ram_price_history ram_price;

    @Column(name = "discount")
    private float discount;

    @Column(name = "total_price",nullable = false)
    private float total_price;

    @Column(name = "order_date", columnDefinition = "timestamp")
	@DateTimeFormat(fallbackPatterns = "dd/MM/yyyy")
    private LocalDateTime order_date;

  
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
    public Float getTotalPrice(){
        this.total_price = cpu_price.getLast_price() +
                            gpu_price.getLast_price()+
                            ram_price.getLast_price()+
                            motherboard_price.getLast_price()+
                            storage_price.getLast_price();
        if (discount != 0){
            this.total_price *=discount;
        }
        return this.total_price;
    }
}
