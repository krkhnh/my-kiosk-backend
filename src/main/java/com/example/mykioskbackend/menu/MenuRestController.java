package com.example.mykioskbackend.menu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuRestController {
	@NonNull
	private final MenuRepository menuRepository;

	@GetMapping
	public List<Menu> getMenus() {
		return menuRepository.findAll();
	}
}
