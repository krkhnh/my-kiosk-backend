package com.example.mykioskbackend.order;

import com.example.mykioskbackend.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@RequiredArgsConstructor
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
	@JsonIgnore
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "MENU_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private Menu menu;

	@Column(name = "MENU_ID", nullable = false)
	private Long menuId;

	@Column(name = "QUANTITY", nullable = false)
	private Short quantity;

	@Column(name = "MENU_PRICE", nullable = false)
	private Integer menuPrice;

	public OrderItem(@NonNull OrderItemDto orderItemDto) {
		menuId = orderItemDto.getMenuId();
		quantity = orderItemDto.getQuantity();
		menuPrice = orderItemDto.getMenuPrice();
	}

	@SuppressWarnings("unused")
	public void setMenu(Menu menu) {
		if (this.menu == menu) {
			return; // 중복 실행 방지
		}

		this.menu = menu;
	}

	@SuppressWarnings("unused")
	public void setOrder(Order order) {
		if (this.order == order) {
			return; // 중복 실행 방지
		}

		this.order = order;
		if (order != null) {
			order.addOrderItem(this);
		}
	}
}