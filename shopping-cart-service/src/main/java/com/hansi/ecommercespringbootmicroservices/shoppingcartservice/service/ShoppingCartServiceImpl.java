package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.service;

import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions.CartItemNotFoundException;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions.ProductUpdateException;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions.ShoppingCartNotFoundException;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.CartItem;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.ShoppingCart;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.repository.CartItemRepository;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.repository.ShoppingCartRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CartItemRepository cartItemRepository;


    @Override
    public ShoppingCart createShoppingCart(Long userId) {
        ShoppingCart shoppingCart = new ShoppingCart().builder().userId(userId).build();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public CartItem addItemToCart(
            Long cartId,
            Long productId,
            String productName,
            int quantity,
            BigDecimal pricePerItem) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart not found with id " + cartId));

        try {
            ResponseEntity<?> getProductById = restTemplate.getForEntity("http://localhost:8081/products/" + productId, Object.class);

            if (getProductById.getStatusCode() != HttpStatus.OK) {
                throw new ProductUpdateException("Product is not available.");
            }
        } catch (HttpClientErrorException ex) {
            throw new ProductUpdateException("Product is not available.");
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setProductId(productId);
        cartItem.setProductName(productName);
        cartItem.setQuantity(quantity);
        cartItem.setPricePerItem(pricePerItem);

        shoppingCart.addCartItem(cartItem);

        try {
            ResponseEntity<String> updateQuantity = restTemplate.exchange(
                    "http://localhost:8081/products/" + productId + "/update-quantity?quantityChange=" + (quantity * -1)
                    , HttpMethod.PUT
                    , null
                    , String.class);

            if (updateQuantity.getStatusCode() != HttpStatus.OK) {
                throw new ProductUpdateException("Product quantity not available.");
            }
        } catch (HttpClientErrorException ex) {
            throw new ProductUpdateException("Product quantity not available.");
        }
        cartItemRepository.save(cartItem);
        shoppingCartRepository.save(shoppingCart);


        return cartItem;
    }

    @Override
    public void updateCartItemQuantity(
            @NotNull Long cartItemId,
            @Positive int newQuantity) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found by id " + cartItemId));
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);

    }

    @Override
    public void removeCartItem(@NotNull Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found by id " + cartItemId));
        cartItemRepository.delete(cartItem);

    }

    @Override
    public void clearCart(@NotNull Long cartId) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart not found with id " + cartId));
        shoppingCart.getCartItems().clear();

    }

    @Override
    public ShoppingCart getCartById(@NotNull Long cartId) {

        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart not found with id " + cartId));
    }
}
