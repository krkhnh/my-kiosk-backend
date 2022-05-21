package com.example.mykioskbackend.order;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin
@RequiredArgsConstructor
public class OrderRestController {
	@NonNull
	private final OrderRepository orderRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postOrders(@RequestBody @Valid PostOrdersDto postOrdersDto) {
		Order order = new Order(postOrdersDto);
		orderRepository.save(order);
	}
}
