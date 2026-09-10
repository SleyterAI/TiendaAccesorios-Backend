package com.ConsigueVentas.TiendaAccesorios.User.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
