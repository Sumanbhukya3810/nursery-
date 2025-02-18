package com.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    private String itemName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull
    private Double price;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    public Item() {
        // Default constructor
    }

    public Item(String itemName, Integer quantity, Double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        updateTotalPrice();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public void setPrice(Double price) {
        this.price = price;
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        if (price != null && quantity != null) {
            this.totalPrice = price * quantity;
        } else {
            this.totalPrice = null; // or some default value if needed
        }
    }
}
