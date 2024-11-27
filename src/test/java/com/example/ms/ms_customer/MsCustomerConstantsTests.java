package com.example.ms.ms_customer;

import com.example.ms.ms_customer.constants.CustomersConstants;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;


class MsCustomerConstantsTests {

	@Test
	void testPrivateConstructorThrowsException() {
		Constructor<?>[] constructors = CustomersConstants.class.getDeclaredConstructors();

		for (Constructor<?> constructor : constructors) {
			constructor.setAccessible(true); // Permite el acceso al constructor privado

			assertThrows(InvocationTargetException.class, () -> {
				constructor.newInstance();
			}, "Instantiating CustomersConstants should throw an exception");
		}
	}

	@Test
	void testConstantValues() {
		assertEquals("201", CustomersConstants.STATUS_201);
		assertEquals("Client created successfully", CustomersConstants.MESSAGE_201);

		assertEquals("200", CustomersConstants.STATUS_200);
		assertEquals("Request processed successfully", CustomersConstants.MESSAGE_200);

		assertEquals("400", CustomersConstants.STATUS_400);
		assertEquals("Customer already exists with given DNI", CustomersConstants.MESSAGE_400);

		assertEquals("417", CustomersConstants.STATUS_417);
		assertEquals("Update operation failed. Please try again or contact the Dev team", CustomersConstants.MESSAGE_417_UPDATE);
		assertEquals("Delete operation failed. Please try again or contact the Dev team", CustomersConstants.MESSAGE_417_DELETE);

		assertEquals("500", CustomersConstants.STATUS_500);
		assertEquals("An error occurred. Please try again or contact the Dev team", CustomersConstants.MESSAGE_500);
	}

}



