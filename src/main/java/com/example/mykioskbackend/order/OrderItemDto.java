package com.example.mykioskbackend.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
	@NotNull
	private Long menuId;
	@NotNull
	private Integer unitPrice;
	@NotNull
	private Short qty;
}