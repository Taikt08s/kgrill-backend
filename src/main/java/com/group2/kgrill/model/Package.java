package com.group2.kgrill.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String ingredient;
    private String code;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "package_type_id")
    private PackageType packageType;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private PackageSize size;

    @OneToMany(mappedBy = "packageEntity")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "packageEntity")
    private List<PackageDish> packageDishes;
}

