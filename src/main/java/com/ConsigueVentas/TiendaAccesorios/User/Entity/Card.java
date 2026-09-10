package com.ConsigueVentas.TiendaAccesorios.User.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private Customer customer;
}
