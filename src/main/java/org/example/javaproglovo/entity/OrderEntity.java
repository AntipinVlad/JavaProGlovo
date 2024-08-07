package org.example.javaproglovo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.javaproglovo.enums.OrderStatus;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders", schema = "public")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @JsonProperty("status")
    @Column(nullable = false, columnDefinition = "varchar(35) default 'IN_PROCESSING'")
    private OrderStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "checkout_date", nullable = false, columnDefinition = "timestamp default now()")
    @ColumnDefault("CURRENT_DATE")
    private LocalDateTime checkoutDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "order_id")
    private List<ItemEntity> items = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        checkoutDate = Optional.ofNullable(checkoutDate).orElse(LocalDateTime.now());
        status = Optional.ofNullable(status).orElse(OrderStatus.IN_PROCESSING);
    }
}
