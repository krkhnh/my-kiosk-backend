package com.example.mykioskbackend.menucategory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/menuCategories")
@CrossOrigin
@RequiredArgsConstructor
public class MenuCategoryRestController {
    @NonNull
    private final MenuCategoryRepository menuCategoryRepository;

    @GetMapping("/{id}")
    public GetMenuCategoriesRespDto getMenuCategoriesId(@PathVariable Long id) {
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new GetMenuCategoriesRespDto(menuCategory);
    }

    @GetMapping
    public List<GetMenuCategoriesRespDto> getMenuCategories(
            @RequestParam(required = false, defaultValue = "") List<Long> ids) {
        List<MenuCategory> menuCategories =
                ids.isEmpty() ? menuCategoryRepository.findAll() : menuCategoryRepository.findAllById(ids);
        return menuCategories.stream()
                .map(GetMenuCategoriesRespDto::new)
                .toList();
    }
}
