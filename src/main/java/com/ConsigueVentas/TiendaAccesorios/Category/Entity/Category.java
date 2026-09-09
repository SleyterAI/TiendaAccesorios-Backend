package com.ConsigueVentas.TiendaAccesorios.Category.Entity;

import com.ConsigueVentas.TiendaAccesorios.Product.Entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    /*@OneToMany(mappedBy = "category")
    private List<Product> product = new ArrayList<>();*/
}
