package org.example.javaproglovo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", schema = "public")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("Ukraine")
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private double price;
}
