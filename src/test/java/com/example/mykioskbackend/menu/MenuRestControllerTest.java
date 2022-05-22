package com.example.mykioskbackend.menu;

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

class MenuRestControllerTest extends CommonTest {
    @Test
    void getMenusId__200() throws Exception {
        long menuId = 1L;
        MvcResult mvcResult = performGetMenusId(menuId)
                .andExpect(status().isOk())
                .andReturn();

        GetMenusRespDto getMenusRespDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                GetMenusRespDto.class);
        assertEquals(menuId, getMenusRespDto.getId());
    }

    @Test
    void getMenusId__wrongId__404() throws Exception {
        performGetMenusId(0)
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getMenus__200() throws Exception {
        MvcResult mvcResult = performGetMenus(null)
                .andExpect(status().isOk())
                .andReturn();

        List<GetMenusRespDto> getMenusRespDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });
        assertEquals(4, getMenusRespDtos.size());
    }

    @Test
    void getMenus__2ids__200() throws Exception {
        MvcResult mvcResult = performGetMenus(new String[]{"1", "3"})
                .andExpect(status().isOk())
                .andReturn();

        List<GetMenusRespDto> getMenusRespDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });
        assertEquals(2, getMenusRespDtos.size());

        Set<Long> menuIds = getMenusRespDtos.stream()
                .map(GetMenusRespDto::getId)
                .collect(Collectors.toSet());
        assertTrue(menuIds.contains(1L));
        assertTrue(menuIds.contains(3L));
    }

}