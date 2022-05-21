package com.example.mykioskbackend.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`ORDER`")
@Getter
@RequiredArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private final List<OrderItem> orderItems = new ArrayList<>();

	public Order(@NonNull PostOrdersDto postOrdersDto) {
		for (OrderItemDto orderItemDto : postOrdersDto.getOrderItems()) {
			addOrderItem(new OrderItem(orderItemDto));
		}
	}

	@SuppressWarnings("unused")
	public void addOrderItem(@NonNull OrderItem orderItem) {
		if (orderItems.contains(orderItem)) {
			return; // 중복 실행 방지
		}

		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	@SuppressWarnings("unused")
	public void removeOrderItem(@NonNull OrderItem orderItem) {
		orderItems.remove(orderItem);
		orderItem.setOrder(null);
	}
}