package com.order.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	private long id;
	private String customerEmail;
	private String customerAddress;
	private List<OrderItem> items;

}
