package com.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.bean.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
	//Order findById(long id);
}
