package com.group2.kgrill.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class PackageDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dish_price")
    private Float dishPrice;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageEntity;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
}

