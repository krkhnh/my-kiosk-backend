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
public class GetMenusRespDto {
	private Long id;
	private String name;
	private Integer price;
	private List<Long> menuCategoryIds = new ArrayList<>();

	public GetMenusRespDto(@NonNull Menu menu) {
		setId(menu.getId());
		setName(menu.getName());
		setPrice(menu.getPrice());
		setMenuCategoryIds(menu.getMenuCategoryIds());
	}
}