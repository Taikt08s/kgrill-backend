package com.group2.kgrill.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date orderDate;
    private Float orderValue;
    private Date shippedDate;
    private String shippedAddress;
    private Float shippingFee;
    private String status;
    private String code;
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User account;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

    @OneToMany(mappedBy = "order")
    @Column(name = "order_details")
    private List<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "currentOrder")
    private User accountWithCurrentOrder;
}
