package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.controller;

import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions.ProductUpdateException;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.CartItem;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.ShoppingCart;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.service.ShoppingCartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestParam @NotNull Long userId){
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCart);
    }

    @PostMapping("/items")
    public ResponseEntity<?> addItemsToCart(
            @RequestParam @NotNull Long cartId,
            @RequestParam @NotNull Long productId,
            @RequestParam @NotBlank String productName,
            @RequestParam int quantity,
            @RequestParam @PositiveOrZero BigDecimal pricePerItem
    ){
        CartItem item = null;
        try {
            item = shoppingCartService.addItemToCart(cartId, productId, productName, quantity, pricePerItem);
        } catch (ProductUpdateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        if( item != null ){
            return ResponseEntity.status(HttpStatus.CREATED).body(item);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable Long id) {
        ShoppingCart shoppingCart = shoppingCartService.getCartById(id);
        if (shoppingCart != null) {
            return ResponseEntity.ok(shoppingCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
