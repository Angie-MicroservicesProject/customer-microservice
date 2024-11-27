package com.example.ms.ms_customer;


import com.example.ms.ms_customer.constants.CustomersConstants;
import com.example.ms.ms_customer.controller.CustomerController;
import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.dto.ResponseDto;
import com.example.ms.ms_customer.model.entity.Customer;
import com.example.ms.ms_customer.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MsCustomerControllerTests {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;



	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	void testCreateCustomer() throws Exception {
		// Arrange
		CustomerDto customerDto = new CustomerDto("John", "Doe", "12345678", "john.doe@example.com");
		ResponseDto responseDto = new ResponseDto(CustomersConstants.STATUS_201, CustomersConstants.MESSAGE_201);

		doNothing().when(customerService).createCustomer(customerDto);

		// Act & Assert
		mockMvc.perform(post("/api/customers")
						.contentType("application/json")
						.content("{\"name\":\"John\",\"lastname\":\"Doe\",\"dni\":\"12345678\",\"email\":\"john.doe@example.com\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.statusCode").value("201")) // Cambiar a "statusCode"
				.andExpect(jsonPath("$.statusMsg").value("Client created successfully")); // Cambiar a "statusMsg"

	}

	@Test
	void testGetCustomers() throws Exception {
		// Arrange
		when(customerService.getAllCustomers()).thenReturn(List.of(new Customer(1L,"John", "Doe", "12345678", "john.doe@example.com")));

		// Act and Assert
		mockMvc.perform(get("/api/customers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("John"))
				.andExpect(jsonPath("$[0].lastname").value("Doe"))
				.andExpect(jsonPath("$[0].dni").value("12345678"))
				.andExpect(jsonPath("$[0].email").value("john.doe@example.com"));

		// Verify that the service method was called
		verify(customerService, times(1)).getAllCustomers();
	}
	@Test
	void testGetCustomerByDni() throws Exception {
		// Arrange
		CustomerDto customerDto = new CustomerDto("John", "Doe", "12345678", "john.doe@example.com");
		when(customerService.getCustomer("12345678")).thenReturn(customerDto);

		// Act and Assert
		mockMvc.perform(get("/api/customers/{dni}", "12345678"))
				.andExpect(status().isOk()) // Asegúrate de que el código de estado esperado es 200
				.andExpect(jsonPath("$.name").value("John"))
				.andExpect(jsonPath("$.lastname").value("Doe"))
				.andExpect(jsonPath("$.dni").value("12345678"))
				.andExpect(jsonPath("$.email").value("john.doe@example.com"));

		verify(customerService, times(1)).getCustomer("12345678");
	}


	@Test
	void testUpdateCustomer() throws Exception {
		// Arrange
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName("John");
		customerDto.setLastname("Doe");
		customerDto.setDni("12345678");
		customerDto.setEmail("john.doe@example.com");

		when(customerService.updateCustomer(customerDto)).thenReturn(true);

		// Act
		MvcResult result = mockMvc.perform(put("/api/customers/{id}", "12345678")
						.contentType("application/json")
						.content("{\"name\": \"John\", \"lastname\": \"Doe\", \"dni\": \"12345678\", \"email\": \"john.doe@example.com\"}"))
				.andExpect(status().isOk())
				.andReturn();

		// Imprimir el contenido de la respuesta para verificar la estructura
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	void testDeleteCustomer() throws Exception {
		// Arrange
		when(customerService.deleteCustomer("12345678")).thenReturn(true);

		// Act and Assert
		mockMvc.perform(delete("/api/customers")
						.param("dni", "12345678"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value("200"))
				.andExpect(jsonPath("$.statusMsg").value("Request processed successfully"));

		verify(customerService, times(1)).deleteCustomer("12345678");
	}



	@Test
	void contextLoads() {
		System.out.println("Demo test");
	}

}
