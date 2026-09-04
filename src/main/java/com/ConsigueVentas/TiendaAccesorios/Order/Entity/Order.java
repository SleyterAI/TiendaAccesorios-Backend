package com.ConsigueVentas.TiendaAccesorios.Order.Entity;

import com.ConsigueVentas.TiendaAccesorios.User.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    @Pattern(
            regexp = "^[0-9]{9}$",
            message = "El celular debe tener 9 dígitos"
    )
    private String phoneNumber;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 30)
    private String status;
    //pendiente, en preparacion, entregado

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderDetail> orderDetail = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
