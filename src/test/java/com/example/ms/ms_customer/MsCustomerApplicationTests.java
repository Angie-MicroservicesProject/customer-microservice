package com.example.ms.ms_customer;

import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.exception.CustomerAlreadyExistsException;
import com.example.ms.ms_customer.exception.ResourceNotFoundException;
import com.example.ms.ms_customer.model.entity.Customer;
import com.example.ms.ms_customer.repository.CustomerRepository;
import com.example.ms.ms_customer.service.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MsCustomerApplicationTests {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;


	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateCustomer() {
		// Arrange
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName("John");
		customerDto.setLastname("Doe");
		customerDto.setDni("12345678");
		customerDto.setEmail("john.doe@example.com");

		when(customerRepository.findByDni("12345678")).thenReturn(Optional.empty());
		when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
			Customer savedCustomer = invocation.getArgument(0);
			savedCustomer.setCustomerId(1L); // Simula un ID generado
			return savedCustomer;
		});

		// Act
		customerService.createCustomer(customerDto);

		// Assert
		verify(customerRepository).findByDni("12345678"); // Verifica que findByDni fue llamado
		verify(customerRepository).save(any(Customer.class)); // Verifica que save fue llamado
	}

	@Test
	void testCreateCustomerAlreadyExists() {
		// Arrange
		CustomerDto customerDto = new CustomerDto("John", "Doe", "12345678", "john.doe@example.com");
		Customer existingCustomer = new Customer();
		existingCustomer.setDni("12345678");

		when(customerRepository.findByDni("12345678")).thenReturn(Optional.of(existingCustomer));

		// Act & Assert
		assertThrows(CustomerAlreadyExistsException.class, () -> customerService.createCustomer(customerDto));
		verify(customerRepository).findByDni("12345678");
		verify(customerRepository, never()).save(any(Customer.class));
	}



	@Test
	void testGetAllCustomers() {
		// Arrange
		Customer customer1 = new Customer(1L, "John", "Doe", "12345678", "john.doe@example.com");
		Customer customer2 = new Customer(2L, "Jane", "Doe", "87654321", "jane.doe@example.com");

		when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

		// Act
		List<Customer> result = customerService.getAllCustomers();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(customerRepository).findAll();
	}


	@Test
	void testGetCustomer() {
		// Arrange
		Customer customer = new Customer(1L, "John", "Doe", "12345678", "john.doe@example.com");

		when(customerRepository.findByDni("12345678")).thenReturn(Optional.of(customer));

		// Act
		CustomerDto result = customerService.getCustomer("12345678");

		// Assert
		assertNotNull(result);
		assertEquals("John", result.getName());
		assertEquals("Doe", result.getLastname());
		verify(customerRepository).findByDni("12345678");
	}

	@Test
	void testGetCustomerNotFound() {
		// Arrange
		when(customerRepository.findByDni("12345678")).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomer("12345678"));
		verify(customerRepository).findByDni("12345678");
	}


	@Test
	void testUpdateCustomer() {
		// Arrange
		CustomerDto customerDto = new CustomerDto("John", "Smith", "12345678", "john.smith@example.com");
		Customer existingCustomer = new Customer(1L, "John", "Doe", "12345678", "john.doe@example.com");

		when(customerRepository.findByDni("12345678")).thenReturn(Optional.of(existingCustomer));
		when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

		// Act
		boolean result = customerService.updateCustomer(customerDto);

		// Assert
		assertTrue(result);
		verify(customerRepository).findByDni("12345678");
		verify(customerRepository).save(any(Customer.class));
	}

	@Test
	void testUpdateCustomerNotFound() {
		// Arrange
		CustomerDto customerDto = new CustomerDto("John", "Smith", "12345678", "john.smith@example.com");

		when(customerRepository.findByDni("12345678")).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(CustomerAlreadyExistsException.class, () -> customerService.updateCustomer(customerDto));
		verify(customerRepository).findByDni("12345678");
		verify(customerRepository, never()).save(any(Customer.class));
	}

	@Test
	void testDeleteCustomer() {
		// Arrange
		Customer customer = new Customer(1L, "John", "Doe", "12345678", "john.doe@example.com");

		when(customerRepository.findByDni("12345678")).thenReturn(Optional.of(customer));
		doNothing().when(customerRepository).deleteById(1L);

		// Act
		boolean result = customerService.deleteCustomer("12345678");

		// Assert
		assertTrue(result);
		verify(customerRepository).findByDni("12345678");
		verify(customerRepository).deleteById(1L);
	}

	@Test
	void testDeleteCustomerNotFound() {
		// Arrange
		when(customerRepository.findByDni("12345678")).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer("12345678"));
		verify(customerRepository).findByDni("12345678");
		verify(customerRepository, never()).deleteById(anyLong());
	}


	@Test
	void contextLoads() {
		System.out.println("Demo test");
	}

}
