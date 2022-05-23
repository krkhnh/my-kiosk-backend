package com.example.mykioskbackend;

import com.example.mykioskbackend.menu.MenuRepository;
import com.example.mykioskbackend.order.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public abstract class CommonTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected EntityManager entityManager;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected MenuRepository menuRepository;

    protected ResultActions performPostOrders(@NonNull String requestBody) throws Exception {
        return mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print());
    }

    protected ResultActions performGetMenus(String menuCategoryId) throws Exception {
        MockHttpServletRequestBuilder getMenusBuilder = get("/menus");
        if (menuCategoryId != null) {
            getMenusBuilder = getMenusBuilder.queryParam("menuCategoryId", menuCategoryId);
        }
        return mockMvc.perform(getMenusBuilder)
                .andDo(print());
    }

    protected ResultActions performGetMenusId(long menuId) throws Exception {
        return mockMvc.perform(get("/menus/" + menuId))
                .andDo(print());
    }

    protected ResultActions performGetMenuCategories() throws Exception {
        return mockMvc.perform(get("/menuCategories"))
                .andDo(print());
    }

    protected ResultActions performGetMenuCategoriesId(long menuCategoryId) throws Exception {
        return mockMvc.perform(get("/menuCategories/" + menuCategoryId))
                .andDo(print());
    }
}