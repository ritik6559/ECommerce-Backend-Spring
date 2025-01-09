package com.ritik.dreamshop.dto.cart;

import com.ritik.dreamshop.dto.product.ProductDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;
}
