package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Entity.Cart;
import com.example.Entity.Item;

@Service
public interface CartService {
	
	Cart createCart(Cart cart);
	
    Cart addItemToCart(Integer cartId, Item item);
    
    Cart updateItemInCart(Integer cartId, Integer itemId, Item item);
    
    Cart removeItemFromCart(Integer cartId, Integer itemId);
    
    Cart getCartById(Integer cartId);

}
