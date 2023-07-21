package com.brazil.erudio.mockito;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

public class OrderService {

    public Order createOrder(String productName, Long amount, String orderID) {

        Order order = new Order();

        order.setId(orderID == null ? randomUUID().toString() : orderID);
        order.setCreationDate(now());
        order.setAmount(amount);
        order.setProductName(productName);

        return order;
    }

}
