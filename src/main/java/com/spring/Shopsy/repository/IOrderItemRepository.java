package com.spring.Shopsy.repository;

import com.spring.Shopsy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {

}