package com.anobel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.List;

@ToString
@Setter
@Getter
@Entity


@Table(name = "clients")
public class Client  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message = "must be valid email address")
    @Column(name = "email", nullable = false,unique = true)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "discount")
    private double discount;
    @Column(name = "orders_number")
    private int orders_number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
	
    @ManyToMany()
    @JoinTable(name = "active_orders",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    @ToString.Exclude
    private List<Order> orders;

    public Client() {
        Role user = new Role();
        user.setId(1L);
        this.role = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getId() != null && getId().equals(client.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


   


}
