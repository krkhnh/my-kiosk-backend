package com.example.mykioskbackend.order;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostOrdersDto {
	@Valid
	@NotEmpty
	private List<OrderItemDto> orderItems = new ArrayList<>();
}