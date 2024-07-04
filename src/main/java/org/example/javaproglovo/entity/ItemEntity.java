package org.example.javaproglovo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table("Items")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private int quantity;
    @ManyToOne(cascade = CascadeType.DETACH)
    private OrderEntity order;

    @OneToOne(cascade = CascadeType.DETACH)
    private ProductEntity product;
}
