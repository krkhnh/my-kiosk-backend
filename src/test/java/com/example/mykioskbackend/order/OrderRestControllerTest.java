package com.example.mykioskbackend.order;

import com.example.mykioskbackend.CommonTest;
import com.example.mykioskbackend.menu.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Rollback(false)
class OrderRestControllerTest extends CommonTest {

	@Test
	void postOrders__201() throws Exception {
		//Given
		long menuId = 1;
		Menu menu = menuRepository.findById(menuId).orElseThrow();

		PostOrdersDto postOrdersDto = new PostOrdersDto();
		postOrdersDto.getOrderItems().add(new OrderItemDto(menuId, menu.getPrice(), (short) 3));

		//When
		performPostOrders(objectMapper.writeValueAsString(postOrdersDto))
				.andExpect(status().isCreated());

		//Then
		entityManager.clear();
		Order order = orderRepository.findById(1L).orElseThrow();
		assertEquals(1, order.getOrderItems().size());
		OrderItem orderItem = order.getOrderItems().get(0);
		assertEquals(order, orderItem.getOrder());
		assertEquals(postOrdersDto.getOrderItems().get(0).getMenuId(), orderItem.getMenu().getId());
		assertEquals(postOrdersDto.getOrderItems().get(0).getUnitPrice(), orderItem.getMenu().getPrice());
	}

	@Test
	void postOrders__noRequestBody__400() throws Exception {
		performPostOrders("")
				.andExpect(status().isBadRequest());
	}
}