package com.example.mykioskbackend.menucategory;

import com.example.mykioskbackend.CommonTest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuCategoryRestControllerTest extends CommonTest {
    @Test
    void getMenuCategoriesId__200() throws Exception {
        long menuCategoryId = 1L;
        MvcResult mvcResult = performGetMenuCategoriesId(menuCategoryId)
                .andExpect(status().isOk())
                .andReturn();

        GetMenuCategoriesRespDto getMenuCategoriesRespDto = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                GetMenuCategoriesRespDto.class);
        assertEquals(menuCategoryId, getMenuCategoriesRespDto.getId());
    }

    @Test
    void getMenuCategories__wrongId__404() throws Exception {
        performGetMenuCategoriesId(0)
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getMenuCategories__200() throws Exception {
        MvcResult mvcResult = performGetMenuCategories(null)
                .andExpect(status().isOk())
                .andReturn();

        List<GetMenuCategoriesRespDto> getMenuCategoriesRespDtos = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });
        assertEquals(2, getMenuCategoriesRespDtos.size());
    }

    @Test
    void getMenuCategories__2ids__200() throws Exception {
        MvcResult mvcResult = performGetMenuCategories(new String[]{"1"})
                .andExpect(status().isOk())
                .andReturn();

        List<GetMenuCategoriesRespDto> getMenuCategoriesRespDtos = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });
        assertEquals(1, getMenuCategoriesRespDtos.size());

        Set<Long> menuIds = getMenuCategoriesRespDtos.stream()
                .map(GetMenuCategoriesRespDto::getId)
                .collect(Collectors.toSet());
        assertTrue(menuIds.contains(1L));
    }

}