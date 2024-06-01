package com.group2.kgrill.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String method;
}

