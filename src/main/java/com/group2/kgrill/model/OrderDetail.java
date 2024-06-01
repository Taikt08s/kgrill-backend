package com.group2.kgrill.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Float comboPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DeliveryOrder order;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageEntity;

    @OneToMany(mappedBy = "orderDetail")
    private List<Feedback> feedbacks;
}

