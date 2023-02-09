package com.anobel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.List;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;


    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message = "must be valid email adress")
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Pattern(regexp = "[A-Za-z\\d]{6,}", message="Must be 6 symbols long")
    @Pattern(regexp = ".*\\d.*", message = "Must have at least one number")
    @Pattern(regexp = ".*[A-Z].*", message="Must have at east one uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message="Must have at east one lowercase letter")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name",nullable = false)
    private String secondName;

    @Column(name = "discount")
    private double discount;
    @Column(name = "orders_number")
    private int orders_number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "active_orders",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    @ToString.Exclude
    private List<Order> orders;


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
