package org.example.javaproglovo.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.javaproglovo.dto.*;
import org.example.javaproglovo.repository.ItemRepository;
import org.example.javaproglovo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@SpringBootTest
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    ItemRepository itemRepository;
    private final ObjectMapper mapper = new ObjectMapper();
    private static OrderDto orderDto;
    private static ItemDto orderItemDto;

    @BeforeAll
    public static void setUp(@Autowired OrderService orderService, @Autowired ProductService productService) {
        orderDto = OrderDto.builder()
                .customerName("Customer1").checkoutDate(LocalDateTime.now()).build();
        orderDto = orderService.save(orderDto);

        ProductDto productDto = ProductDto.builder().name("Product1").build();
        productDto = productService.save(productDto);

        orderItemDto = ItemDto.builder().quantity(456).price(34.12).productId(productDto.getId()).build();
    }

    @BeforeEach
    public void init() {
        itemRepository.deleteAll();

        orderDto = orderService.addItem(orderDto.getId(), orderItemDto);

        int orderItemId = orderDto.getItems().getFirst();
        orderItemDto = itemService.getById(orderItemId);
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(orderItemDto)), true));
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/items/{id}", orderItemDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(orderItemDto), true));
    }

    @Test
    public void updateTest() throws Exception {
        orderItemDto.setQuantity(13);

        mockMvc.perform(put("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(orderItemDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(orderItemDto), true));
    }

    @Test
    public void deleteTest() throws Exception {
        int id = orderItemDto.getId();

        mockMvc.perform(delete("/items/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
