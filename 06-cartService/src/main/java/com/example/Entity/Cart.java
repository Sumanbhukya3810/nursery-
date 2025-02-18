package com.example.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cartId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items= new ArrayList<>();

    public Cart() {}

    // Optionally, add methods to manage items
    public void addItem(Item item) {
        items.add(item);
        item.setCart(this); // Ensure bidirectional relationship is maintained
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setCart(null); // Ensure bidirectional relationship is maintained
    }
}
