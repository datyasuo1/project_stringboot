package com.example.orderdemo.controller;

import com.example.orderdemo.entity.CartItem;
import com.example.orderdemo.entity.CartItemId;
import com.example.orderdemo.entity.Product;
import com.example.orderdemo.entity.ShoppingCart;
import com.example.orderdemo.entity.dto.CartItemDTO;
import com.example.orderdemo.entity.dto.ShoppingCartDTO;
import com.example.orderdemo.repository.ProductRepository;
import com.example.orderdemo.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1")
public class ShoppingCartApi {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.POST, path = "add-to-cart")
    public void saveCart(@RequestParam String userId, @RequestBody ShoppingCartDTO shoppingCartDTO){
        boolean hasException = false;
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .shipName(shoppingCartDTO.getShipName())
                .shipAddress(shoppingCartDTO.getShipAddress())
                .shipNote(shoppingCartDTO.getShipNote())
                .shipPhone(shoppingCartDTO.getShipPhone())
                .isShoppingCart(true)
                .build();
        Set<CartItem> setCartItem = new HashSet<>();
        System.out.println(shoppingCart.getId());
        for (CartItemDTO cartItemDTO :
                shoppingCartDTO.getItems()) {
            Optional<Product> optionalProduct = productRepository.findById(cartItemDTO.getProductId());
            if(!optionalProduct.isPresent()){
                hasException = true;
                break;
            }
            Product product = optionalProduct.get();
            CartItem cartItem = CartItem.builder()
                    .id(new CartItemId(shoppingCart.getId(), product.getProductId()))
                    .productName(product.getName())
                    .productImage(product.getThumbnails())
                    .quantity(cartItemDTO.getQuantity())
                    .unitPrice(product.getPrice())
                    .shoppingCart(shoppingCart)
                    .build();

            shoppingCart.addTotalPrice(cartItem);
            setCartItem.add(cartItem);
        }
        shoppingCart.setItems(setCartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    @RequestMapping(method = RequestMethod.POST, path = "orders")
    public void order(@RequestParam String shoppingCartId){
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if (shoppingCart.isPresent()){
          ShoppingCart shoppingCart1 = shoppingCart.get();
          shoppingCart1.setIsShoppingCart(false);
            shoppingCartRepository.save(shoppingCart1);
        }
    }
}
