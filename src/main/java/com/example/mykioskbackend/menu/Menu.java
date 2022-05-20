package com.example.mykioskbackend.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Getter
@RequiredArgsConstructor
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "price", nullable = false)
	private Integer price;
}