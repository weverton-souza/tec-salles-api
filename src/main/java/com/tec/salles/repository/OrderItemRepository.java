package com.tec.salles.repository;

import com.tec.salles.entity.OrderItem;
import com.tec.salles.entity.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {}
