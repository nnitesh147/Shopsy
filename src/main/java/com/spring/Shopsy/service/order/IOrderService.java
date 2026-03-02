package com.spring.Shopsy.service.order;

import com.spring.Shopsy.payload.order.OrderDTO;
import com.spring.Shopsy.payload.order.OrderResponse;
import jakarta.transaction.Transactional;

public interface IOrderService {
    @Transactional
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);

    OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    OrderDTO updateOrder(Long orderId, String status);

    OrderResponse getAllSellerOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

}
