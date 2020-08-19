package com.brunon.takeaway.repository;

import com.brunon.takeaway.model.Order;
import com.brunon.takeaway.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus status);
}
