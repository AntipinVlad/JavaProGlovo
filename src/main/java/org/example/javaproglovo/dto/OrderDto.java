package org.example.javaproglovo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.javaproglovo.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor

public class OrderDto {

    private int id;
    private String customerName;
    @JsonProperty("status")
    private OrderStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkoutDate;
    private List<Integer> items;
}
