package com.group2.kgrill.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String status;

    @OneToMany(mappedBy = "shipper")
    private List<DeliveryOrder> orders;
}

