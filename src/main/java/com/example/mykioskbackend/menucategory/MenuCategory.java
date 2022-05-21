package com.example.mykioskbackend.menucategory;

import com.example.mykioskbackend.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MENU_CATEGORY")
@Getter
@RequiredArgsConstructor
public class MenuCategory {
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "menuCategories")
	@JsonIgnore
	private final Set<Menu> menus = new HashSet<>();

	public void addMenu(@NonNull Menu menu) {
		if (menus.contains(menu)) {
			return; // 중복 실행 방지
		}

		menus.add(menu);
		menu.addMenuCategory(this);
	}

	public void removeMenu(@NonNull Menu menu) {
		if (!menus.contains(menu)) {
			return; // 중복 실행 방지
		}

		menus.remove(menu);
		menu.removeMenuCategory(this);
	}

}