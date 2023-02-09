package com.anobel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;



@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)){
            return false;
        }
        Role role = (Role) obj;
        return getId() != null && getId().equals(role.getId());
    }
    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}
