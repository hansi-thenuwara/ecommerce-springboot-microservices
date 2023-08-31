package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.controller;

import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.CartItem;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.ShoppingCart;
import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestParam Long userId){
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCart);
    }

    @PostMapping("/items")
    public ResponseEntity<CartItem> addItemsToCart(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam String productName,
            @RequestParam int quantity,
            @RequestParam BigDecimal pricePerItem
    ){
        CartItem item = shoppingCartService.addItemToCart(cartId, productId, productName, quantity, pricePerItem);
        if( item != null ){
            return ResponseEntity.status(HttpStatus.CREATED).body(item);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
