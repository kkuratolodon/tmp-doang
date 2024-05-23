package com.heymart.authenticate.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void testAdminBuilder() {
        Admin admin = Admin.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Administrator")
                .username("aliceadmin")
                .password("admin123")
                .role("ADMIN")
                .active(true)
                .build();

        assertNotNull(admin, "Admin object should not be null");

        assertEquals(1L, admin.getId(), "Admin ID should be set correctly by the builder");
        assertEquals("Alice", admin.getFirstname(), "Admin firstname should be set correctly by the builder");
        assertEquals("Administrator", admin.getLastname(), "Admin lastname should be set correctly by the builder");
        assertEquals("aliceadmin", admin.getUsername(), "Admin username should be set correctly by the builder");
        assertEquals("admin123", admin.getPassword(), "Admin password should be set correctly by the builder");
        assertEquals("ADMIN", admin.getRole(), "Admin role should be set correctly by the builder");
        assertTrue(admin.isActive(), "Admin active status should be set correctly by the builder");
    }
    @Test
    void testProtectedConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Admin> constructor = Admin.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Admin admin = constructor.newInstance();

        assertNotNull(admin, "Admin object created with protected constructor should not be null");

        assertNull(admin.getUsername(), "Username should be null if not set");
        assertNull(admin.getPassword(), "Password should be null if not set");
        assertFalse(admin.isActive(), "Active should be false if not set");
        assertNull(admin.getRole(), "Role should be null if not set");
    }
    @Test
    void testAdminBuilderToString() {
        String builder = Admin.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Administrator")
                .username("aliceadmin")
                .password("admin123")
                .role("ADMIN")
                .active(true).toString();

        String expectedToString = "Admin.AdminBuilder(id=1, firstName=Alice, lastName=Administrator, password=admin123, username=aliceadmin, role=ADMIN, active=true)";
        assertEquals(expectedToString, builder, "Admin toString should return correctly formatted string");
    }

}
