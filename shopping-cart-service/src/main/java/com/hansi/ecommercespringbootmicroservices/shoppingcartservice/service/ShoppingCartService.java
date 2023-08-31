package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.service;

import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.CartItem;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {

    public ShoppingCart createShoppingCart(Long userId);

    public CartItem addItemToCart(Long cartId, Long productId, String productName, int quantity, BigDecimal pricePerItem);

    public void updateCartItemQuantity(Long cartItemId, int newQuantity);

    public void removeCartItem(Long cartItemId);

    public void clearCart(Long cartId);

    public ShoppingCart getCartById(Long cartId);

}
