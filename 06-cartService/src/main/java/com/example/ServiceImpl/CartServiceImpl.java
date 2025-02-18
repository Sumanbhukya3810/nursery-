package com.example.ServiceImpl;

import com.example.Entity.Cart;
import com.example.Entity.Item;
import com.example.Service.CartService;
import com.example.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        // Save the cart, which initializes the cart with an empty item list if not provided
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart addItemToCart(Integer cartId, Item item) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            item.setCart(cart); // Set the cart for the item
            cart.addItem(item); // Add the item to the cart
            return cartRepository.save(cart); // Save the cart (which cascades to save the item)
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    @Override
    @Transactional
    public Cart updateItemInCart(Integer cartId, Integer itemId, Item updatedItem) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            Optional<Item> itemOpt = cart.getItems().stream()
                                         .filter(item -> item.getItemId().equals(itemId))
                                         .findFirst();
            if (itemOpt.isPresent()) {
                Item item = itemOpt.get();
                item.setItemName(updatedItem.getItemName());
                item.setQuantity(updatedItem.getQuantity());
                item.setPrice(updatedItem.getPrice());
                item.updateTotalPrice(); // Recalculate total price
                return cartRepository.save(cart); // Save the cart after updating the item
            } else {
                throw new RuntimeException("Item not found");
            }
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    @Override
    @Transactional
    public Cart removeItemFromCart(Integer cartId, Integer itemId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            Optional<Item> itemOpt = cart.getItems().stream()
                                         .filter(item -> item.getItemId().equals(itemId))
                                         .findFirst();
            if (itemOpt.isPresent()) {
                Item item = itemOpt.get();
                cart.removeItem(item); // Remove the item from the cart
                return cartRepository.save(cart); // Save the cart after removing the item
            } else {
                throw new RuntimeException("Item not found");
            }
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    @Override
    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
