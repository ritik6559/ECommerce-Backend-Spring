package com.ritik.dreamshop.service.order;

import com.ritik.dreamshop.enums.OrderStatus;
import com.ritik.dreamshop.exception.ResourceNotFoundException;
import com.ritik.dreamshop.model.cart.Cart;
import com.ritik.dreamshop.model.order.Order;
import com.ritik.dreamshop.model.order.OrderItem;
import com.ritik.dreamshop.model.product.Product;
import com.ritik.dreamshop.repository.order.OrderRepository;
import com.ritik.dreamshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public Order placeOrder(Long userId) {
        return null;
    }

    private Order createOrder(Cart cart){
        Order order = new Order();
        // set the user
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;

    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart){
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
              order, product, cartItem.getQuantity(), cartItem.getUnitPrice()
            );
        }).toList();
    }

    private BigDecimal calculatesTotalAmount(List<OrderItem> ordersItemList){
        return ordersItemList
                .stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
