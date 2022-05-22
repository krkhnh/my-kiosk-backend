package com.example.mykioskbackend.menu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/menus")
@CrossOrigin
@RequiredArgsConstructor
public class MenuRestController {
    @NonNull
    private final MenuRepository menuRepository;

    @GetMapping("/{id}")
    public GetMenusRespDto getMenusId(@PathVariable Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new GetMenusRespDto(menu);
    }

    @GetMapping
    public List<GetMenusRespDto> getMenus(@RequestParam(required = false, defaultValue = "") List<Long> ids) {
        List<Menu> menus = ids.isEmpty() ? menuRepository.findAll() : menuRepository.findAllById(ids);
        return menus.stream()
                .map(GetMenusRespDto::new)
                .toList();
    }
}
