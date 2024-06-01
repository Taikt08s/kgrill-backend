package com.group2.kgrill.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DishCategory category;

    @OneToMany(mappedBy = "dish")
    private List<PackageDish> packageDishes;

    @OneToMany(mappedBy = "dish")
    private List<DishIngredient> dishIngredients;
}

