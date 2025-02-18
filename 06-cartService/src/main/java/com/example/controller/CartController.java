package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Cart;
import com.example.Entity.Item;
import com.example.Service.CartService;

@RestController
public class CartController{

	@Autowired
	private CartService cartService;
	
//	@PostMapping("/createCart")
//	public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
//		return ResponseEntity.ok(cartService.createCart(cart));
//	}
	@PostMapping("/createCart")
	public ResponseEntity<Cart> createCart(@RequestBody Cart cartRequest) {
	    Cart cart = new Cart();
	    cart.setCartId(cartRequest.getCartId()); // Set the cart ID if necessary
	    for (Item itemRequest : cartRequest.getItems()) {
	        Item item = new Item();
	        item.setItemName(itemRequest.getItemName());
	        item.setQuantity(itemRequest.getQuantity());
	        item.setPrice(itemRequest.getPrice());
	        item.setTotalPrice(itemRequest.getQuantity() * itemRequest.getPrice());
	        item.setCart(cart); // Set the cart for the item
	        cart.getItems().add(item);
	    }
	    Cart savedCart = cartService.createCart(cart);
	    return ResponseEntity.ok(savedCart);
	}

	
	
}