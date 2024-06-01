package com.group2.kgrill.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class PackageSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number_of_people")
    private Integer numberOfPeople;
    private String size;
}

