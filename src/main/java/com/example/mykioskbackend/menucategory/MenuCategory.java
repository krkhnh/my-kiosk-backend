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
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "menuCategory")
	@JsonIgnore
	private final Set<Menu> menuSet = new HashSet<>();

	public void addMenu(@NonNull Menu menu) {
		if (menuSet.contains(menu)) {
			return; // 중복 실행 방지
		}

		menuSet.add(menu);
		menu.setMenuCategory(this);
	}

	@SuppressWarnings("unused")
	public void removeMenu(@NonNull Menu menu) {
		menuSet.remove(menu);
		menu.setMenuCategory(null);
	}

}