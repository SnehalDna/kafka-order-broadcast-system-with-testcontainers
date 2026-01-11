package com.github.SnehalDna.kafkaOrderSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // This automatically creates Getters, Setters, toString, and equals/hashCode
@AllArgsConstructor // Creates a constructor with all fields
@NoArgsConstructor  // Creates a default constructor (Required for JSON)
public class Order {
    private String orderId;
    private String product;
    private double price;
    private String status; // You can add this for the E2E test we discussed!
}