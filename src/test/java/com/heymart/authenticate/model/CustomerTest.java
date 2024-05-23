package com.heymart.authenticate.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testCustomerConstructorAndGetters() {
        Customer customer = new Customer(1L, "John", "Doe", "pass123", "johndoe", "user", true);

        assertEquals(Long.valueOf(1), customer.getId());
        assertEquals("John", customer.getFirstname());
        assertEquals("Doe", customer.getLastname());
        assertEquals("pass123", customer.getPassword());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("user", customer.getRole());
        assertTrue(customer.isActive());
    }

    @Test
    public void testCustomerBuilder() {
        Customer customer = Customer.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .password("secure123")
                .username("janesmith")
                .role("CUSTOMER")
                .active(false)
                .build();

        assertEquals(Long.valueOf(2), customer.getId());
        assertEquals("Jane", customer.getFirstname());
        assertEquals("Smith", customer.getLastname());
        assertEquals("secure123", customer.getPassword());
        assertEquals("janesmith", customer.getUsername());
        assertEquals("CUSTOMER", customer.getRole());
        assertFalse(customer.isActive());
    }
    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();
        assertNull(customer.getId());
        assertNull(customer.getFirstname());
        assertNull(customer.getLastname());
        assertNull(customer.getPassword());
        assertNull(customer.getUsername());
        assertNull(customer.getRole());

    }
    @Test
    public void testToString() {
        Customer.CustomerBuilder builder = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .password("password123")
                .username("john_doe")
                .role("user")
                .active(true);
        String expected = "Customer.CustomerBuilder(id=1, firstName=John, lastName=Doe, password=password123, username=john_doe, role=user, active=true)";
        assertEquals(expected, builder.toString());
    }

}

