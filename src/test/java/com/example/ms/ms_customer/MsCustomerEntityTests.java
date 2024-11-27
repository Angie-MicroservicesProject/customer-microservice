package com.example.ms.ms_customer;

import com.example.ms.ms_customer.model.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MsCustomerEntity {

	private Customer customer;

	@BeforeEach
	void setup() {
		customer = new Customer();
		customer.setName("John");
		customer.setLastname("Doe");
		customer.setDni("12345678");
		customer.setEmail("   JOHN.DOE@EXAMPLE.COM  "); // Uppercase and spaces to test logic
	}

	@Test
	void testPrePersist() {
		customer.checkFieldBeforeInsert();
		assertThat(customer.getEmail()).isEqualTo("john.doe@example.com");
	}

	@Test
	void testPreUpdate() {
		customer.checkFieldsBeforeUpdate();
		assertThat(customer.getEmail()).isEqualTo("john.doe@example.com");
	}

	@Test
	void testCustomerInitialization() {
		assertThat(customer.getName()).isEqualTo("John");
		assertThat(customer.getLastname()).isEqualTo("Doe");
		assertThat(customer.getDni()).isEqualTo("12345678");
		assertThat(customer.getEmail()).isEqualTo("   JOHN.DOE@EXAMPLE.COM  ");
	}

	@Test
	void testCustomerToString() {
		String result = customer.toString();
		assertThat(result).contains("John")
				.contains("Doe")
				.contains("12345678")
				.contains("JOHN.DOE@EXAMPLE.COM");
	}

	@Test
	void testNoArgsConstructor() {
		Customer emptyCustomer = new Customer();
		assertThat(emptyCustomer.getCustomerId()).isNull();
		assertThat(emptyCustomer.getName()).isNull();
		assertThat(emptyCustomer.getLastname()).isNull();
		assertThat(emptyCustomer.getDni()).isNull();
		assertThat(emptyCustomer.getEmail()).isNull();
	}

	@Test
	void testAllArgsConstructor() {
		Customer fullCustomer = new Customer(1L, "Jane", "Smith", "87654321", "jane.smith@example.com");
		assertThat(fullCustomer.getCustomerId()).isEqualTo(1L);
		assertThat(fullCustomer.getName()).isEqualTo("Jane");
		assertThat(fullCustomer.getLastname()).isEqualTo("Smith");
		assertThat(fullCustomer.getDni()).isEqualTo("87654321");
		assertThat(fullCustomer.getEmail()).isEqualTo("jane.smith@example.com");
	}

}



