package com.example.mykioskbackend;

import com.example.mykioskbackend.order.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
	protected OrderRepository orderRepository;
	@Autowired
	protected EntityManager entityManager;

	protected ResultActions performPostOrders(@NonNull String requestBody) throws Exception {
		return mockMvc.perform(post("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andDo(print());
	}

}