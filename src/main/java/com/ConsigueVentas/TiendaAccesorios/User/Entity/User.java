package com.ConsigueVentas.TiendaAccesorios.User.Entity;

import com.ConsigueVentas.TiendaAccesorios.Order.Entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 30, nullable = false)
    private String username;

    @Column(unique = true,length = 50, nullable = false)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 30)
    private String role;
    //"ADMIN", "USER"

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
