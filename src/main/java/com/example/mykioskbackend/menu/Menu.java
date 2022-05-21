package com.example.mykioskbackend.menu;

import com.example.mykioskbackend.menucategory.MenuCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MENU")
@Getter
@RequiredArgsConstructor
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "price", nullable = false)
	private Integer price;

	@ManyToMany
	@JoinTable(name = "`MENU-MENU_CATEGORY`",
			joinColumns = @JoinColumn(name = "MENU_ID"),
			inverseJoinColumns = @JoinColumn(name = "MENU_CATEGORY_ID"))
	@JsonIgnore
	private final Set<MenuCategory> menuCategories = new HashSet<>();

	public List<Long> getMenuCategoryIds() {
		return menuCategories.stream().map(MenuCategory::getId).toList();
	}

	public void addMenuCategory(@NonNull MenuCategory menuCategory) {
		if (menuCategories.contains(menuCategory)) {
			return; // 중복 실행 방지
		}

		menuCategories.add(menuCategory);
		menuCategory.addMenu(this);
	}

	public void removeMenuCategory(@NonNull MenuCategory menuCategory) {
		if (!menuCategories.contains(menuCategory)) {
			return; // 중복 실행 방지
		}

		menuCategories.remove(menuCategory);
		menuCategory.removeMenu(this);
	}
}