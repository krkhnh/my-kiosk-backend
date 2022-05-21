package com.example.mykioskbackend.menucategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuCategoriesRespDto {
	private Long id;
	private String name;

	public GetMenuCategoriesRespDto(@NonNull MenuCategory menuCategory) {
		setId(menuCategory.getId());
		setName(menuCategory.getName());
	}
}