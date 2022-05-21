package com.example.mykioskbackend.menu;

import com.example.mykioskbackend.CommonTest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuRestControllerTest extends CommonTest {
	@Test
	void getMenus() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/menus"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		List<Menu> menus = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
		});
		assertEquals(4, menus.size());
	}
}