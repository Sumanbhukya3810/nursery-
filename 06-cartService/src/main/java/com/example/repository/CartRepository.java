package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
