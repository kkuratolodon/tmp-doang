package com.heymart.authenticate.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    @Test
    public void testManagerConstructorAndGetters() {
        Manager manager = new Manager(1L, "Alice", "Johnson", "securePass", "alicej", "admin", true, "LocalMart");

        assertEquals(Long.valueOf(1), manager.getId());
        assertEquals("Alice", manager.getFirstname());
        assertEquals("Johnson", manager.getLastname());
        assertEquals("securePass", manager.getPassword());
        assertEquals("alicej", manager.getUsername());
        assertEquals("admin", manager.getRole());
        assertTrue(manager.isActive());
        assertEquals("LocalMart", manager.getSupermarketName());
    }

    @Test
    public void testManagerDefaultConstructor() {
        Manager manager = new Manager();
        assertNull(manager.getId());
        assertNull(manager.getFirstname());
        assertNull(manager.getLastname());
        assertNull(manager.getPassword());
        assertNull(manager.getUsername());
        assertNull(manager.getRole());
        assertFalse(manager.isActive());
        assertNull(manager.getSupermarketName());
    }

    @Test
    public void testManagerBuilder() {
        Manager.ManagerBuilder builder = Manager.builder()
                .id(2L)
                .firstName("Bob")
                .lastName("Smith")
                .password("password1234")
                .username("bobsmith")
                .role("manager")
                .active(true)
                .supermarketName("MegaMart");

        String expected = "Manager.ManagerBuilder(id=2, firstName=Bob, lastName=Smith, password=password1234, username=bobsmith, role=manager, active=true, supermarketName=MegaMart)";
        assertEquals(expected, builder.toString());

        Manager manager = builder.build();
        assertEquals(Long.valueOf(2), manager.getId());
        assertEquals("Bob", manager.getFirstname());
        assertEquals("Smith", manager.getLastname());
        assertEquals("password1234", manager.getPassword());
        assertEquals("bobsmith", manager.getUsername());
        assertEquals("manager", manager.getRole());
        assertTrue(manager.isActive());
        assertEquals("MegaMart", manager.getSupermarketName());
    }
}
