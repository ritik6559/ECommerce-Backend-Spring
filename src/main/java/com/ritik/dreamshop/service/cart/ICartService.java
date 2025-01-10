package com.ritik.dreamshop.service.cart;

import com.ritik.dreamshop.model.cart.Cart;
import com.ritik.dreamshop.model.user.User;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
