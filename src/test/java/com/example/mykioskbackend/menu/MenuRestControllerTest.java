package com.example.mykioskbackend.menu;

import com.example.mykioskbackend.CommonTest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void getMenus__menuCategoryId__200() throws Exception {
        for (Long menuCategoryId : Arrays.asList(1L, 2L)) {
            MvcResult mvcResult = performGetMenus(menuCategoryId.toString())
                    .andExpect(status().isOk())
                    .andReturn();

            List<GetMenusRespDto> getMenusRespDtos = objectMapper.readValue(
                    mvcResult.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    });
            for (GetMenusRespDto getMenusRespDto : getMenusRespDtos) {
                Assertions.assertThat(getMenusRespDto.getMenuCategoryIds()).contains(menuCategoryId);
            }
        }
    }

}