package com.example.mykioskbackend.menu;

import com.example.mykioskbackend.menucategory.MenuCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "MENU_CATEGORY_ID", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private MenuCategory menuCategory;

	public Integer getMenuCategoryId() {
		return menuCategory.getId();
	}

	public void setMenuCategory(MenuCategory menuCategory) {
		if (this.menuCategory == menuCategory) {
			return; // 중복 실행 방지
		}

		this.menuCategory = menuCategory;
		if (menuCategory != null) {
			menuCategory.addMenu(this);
		}
	}
}