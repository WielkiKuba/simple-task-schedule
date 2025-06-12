package com.grafik.grafikManager;

import org.junit.jupiter.api.BeforeEach; // Importuj BeforeEach
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // Importuj AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles; // Importuj ActiveProfiles
import org.springframework.test.web.servlet.MockMvc; // Importuj MockMvc
import org.springframework.transaction.annotation.Transactional; // Importuj Transactional

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// Importuj statyczne metody dla asercji MockMvc i Hamcrest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
// Pamiętaj o asercjach z AssertJ, jeśli ich używasz, np. assertThat
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class GrafikManagerApplicationTests {

	@Autowired
	private MockMvc mockMvc; // Umożliwia symulowanie żądań HTTP do Twoich endpointów

	@Autowired
	private UserService userService; // Wstrzyknij swoje serwisy do przygotowywania danych testowych
	@Autowired
	private WorkService workService;
	@Autowired
	private UserRepository userRepository; // Opcjonalnie, do bezpośredniego czyszczenia/dodawania w repozytorium
	@Autowired
	private WorkRepository workRepository;

	@BeforeEach
	void setUp() {
		workRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void contextLoads() {
	}
	@Test
	void testGetUserEndpoint() throws Exception {

		userService.addUser("Alice");
		userService.addUser("Bob");

		mockMvc.perform(get("/user"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("Alice")))
				.andExpect(jsonPath("$[1].name", is("Bob")));
	}

}
