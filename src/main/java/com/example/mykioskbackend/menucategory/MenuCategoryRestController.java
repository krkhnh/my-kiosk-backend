package com.example.mykioskbackend.menucategory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menuCategories")
@CrossOrigin
@RequiredArgsConstructor
public class MenuCategoryRestController {
	@NonNull
	private final MenuCategoryRepository menuCategoryRepository;

	@GetMapping
	public List<GetMenuCategoriesRespDto> getMenuCategories() {
		return menuCategoryRepository.findAll().stream()
				.map(GetMenuCategoriesRespDto::new)
				.toList();
	}
}
