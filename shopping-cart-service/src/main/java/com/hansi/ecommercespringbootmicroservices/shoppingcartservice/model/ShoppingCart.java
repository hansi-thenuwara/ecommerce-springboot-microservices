package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private long id;

    private long userId;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem item){
        cartItems.add(item);
        item.setShoppingCart(this);
    }

    public void removeCartItem(CartItem item){
        cartItems.remove(item);
        item.setShoppingCart(null);
    }
}
