package com.ConsigueVentas.TiendaAccesorios.User.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    private String address;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Card card;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

}
