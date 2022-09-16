package com.order.service;

import com.order.bean.Order;

public interface OrderService {
	public boolean saveOder(Order order);
	public Order findOrderById (Long id);

}
