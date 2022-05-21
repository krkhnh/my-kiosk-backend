package com.example.mykioskbackend.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuRespDto {
	private Long id;
	private String name;
	private Integer price;
	private List<Long> menuCategoryIds = new ArrayList<>();

	public GetMenuRespDto(@NonNull Menu menu) {
		setId(menu.getId());
		setName(menu.getName());
		setPrice(menu.getPrice());
		setMenuCategoryIds(menu.getMenuCategoryIds());
	}
}