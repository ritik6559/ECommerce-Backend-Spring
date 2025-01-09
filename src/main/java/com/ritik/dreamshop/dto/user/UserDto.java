package com.ritik.dreamshop.dto.user;

import com.ritik.dreamshop.dto.cart.CartDto;
import com.ritik.dreamshop.dto.order.OrderDto;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;

}
