package com.example.mykioskbackend.menu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@CrossOrigin
@RequiredArgsConstructor
public class MenuRestController {
	@NonNull
	private final MenuRepository menuRepository;

	@GetMapping
	public List<GetMenusRespDto> getMenus() {
		return menuRepository.findAll().stream()
				.map(GetMenusRespDto::new)
				.toList();
	}
}
