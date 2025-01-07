package com.ritik.dreamshop.controller.cart;


import com.ritik.dreamshop.exception.ResourceNotFoundException;
import com.ritik.dreamshop.model.cart.Cart;
import com.ritik.dreamshop.response.ApiResponse;
import com.ritik.dreamshop.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {

    private final ICartService cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId){
        try{
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Success", cart));
        } catch( ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId){
        try{
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId){
        try{
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Success", totalPrice));
        } catch( ResourceNotFoundException e ){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }


}
