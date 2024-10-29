package org.fmemetaj.kafkaspringbootordermanagementdemo.repository;

import org.fmemetaj.kafkaspringbootordermanagementdemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
