package com.order.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.bean.Order;
import com.order.bean.OrderItem;
import com.order.persistence.OrderDao;
import com.order.service.OrderServiceImpl;

@SpringBootTest(classes = OrderTestCase.class)
class OrderTestCase {
	@InjectMocks
	private OrderServiceImpl productOrderService;

	@Mock
	private OrderDao productOrderDao;

	private AutoCloseable autoCloseable;

	private List<OrderItem> items;

	private Order order;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);

		OrderItem item1 = new OrderItem(1, 101, 19, 1900.0);
		OrderItem item2 = new OrderItem(2, 102, 20, 2000.0);
		OrderItem item3 = new OrderItem(3, 103, 21, 2100.0);
		OrderItem item4 = new OrderItem(4, 104, 22, 2200.0);
		OrderItem item5 = new OrderItem(5, 105, 23, 2300.0);

		items = new ArrayList<>();
		items.addAll(Arrays.asList(item1, item2, item3, item4, item5));

		order = new Order();
		order.setId(1);
		order.setCustomerEmail("abc@abc.com");
		order.setCustomerAddress("address1");
		order.setItems(items);
	}

	@AfterEach
	void tearDown() throws Exception {
		items.clear();
		autoCloseable.close();
	}

	@Test
	void getOrderByIdTestOne() {
		long orderId = 1;

		Mockito.when(productOrderDao.findById(orderId)).thenReturn(Optional.of(order));
		assertEquals(order, productOrderService.findOrderById(orderId));
	}

	@Test
	void getOrderByIdTestTwo() {
		long orderId = -1;

		Mockito.when(productOrderDao.findById(orderId)).thenReturn(Optional.ofNullable(null));
		assertEquals(order, productOrderService.findOrderById(orderId));
	}

	@Test
	void saveOrderTestOne() {
		Order newOrder = new Order();
		newOrder.setId(1);
		newOrder.setCustomerEmail("abc@abc.com");
		newOrder.setCustomerAddress("address1");
		newOrder.setItems(items);

		Mockito.when(productOrderDao.save(newOrder)).thenReturn(newOrder);
		assertTrue(productOrderService.saveOder(newOrder));
	}

	@Test
	void saveOrderTestTwo() {
		Order newOrder = new Order();
		newOrder.setId(-1);
		newOrder.setCustomerEmail("abc@abc.com");
		newOrder.setCustomerAddress("address1");
		newOrder.setItems(items);

		Mockito.when(productOrderDao.save(newOrder)).thenReturn(null);
		assertFalse(productOrderService.saveOder(newOrder));
	}
}
