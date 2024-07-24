package org.example.javaproglovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private int id;
    private double price;
    private int quantity;
    private int orderId;
    private int productId;
}
