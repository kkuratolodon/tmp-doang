package com.heymart.authenticate.model;
import static org.junit.jupiter.api.Assertions.*;

import com.heymart.authenticate.enums.ApplicationUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
public class UserTest {
    @Test
    void testAdminRoleGrantsAdminAuthorities() {
        User adminUser = new User(1L, "Alice", "Admin", "pass123", "aliceadmin", "ADMIN", true);
        assertEquals(adminUser.getAuthorities(), ApplicationUserRole.ADMIN.getGrantedAuthority(), "Should have admin authorities when role is ADMIN");
    }

    @Test
    void testCustomerRoleGrantsCustomerAuthorities() {
        User customerUser = new User(1L, "Bob", "Customer", "pass123", "bobcust", "CUSTOMER", true);
        assertEquals(customerUser.getAuthorities(), ApplicationUserRole.CUSTOMER.getGrantedAuthority(), "Should have customer authorities when role is CUSTOMER");

    }

    @Test
    void testManagerRoleGrantsManagerAuthorities() {
        User managerUser = new User(1L, "Bob", "Customer", "pass123", "bobcust", "MANAGER", true);
        assertEquals(managerUser.getAuthorities(), ApplicationUserRole.MANAGER.getGrantedAuthority(), "Should have manager authorities when role is MANAGER");
    }

    @Test
    void testAccountStatusActiveReflectsPropertiesCorrectly() {
        User activeUser = new User(1L, "Charlie", "User", "pass123", "charlieuser", "CUSTOMER", true);
        assertTrue(activeUser.isAccountNonExpired(), "Account should be non-expired when active is true");
        assertTrue(activeUser.isAccountNonLocked(), "Account should be non-locked when active is true");
        assertTrue(activeUser.isCredentialsNonExpired(), "Credentials should be non-expired when active is true");
        assertTrue(activeUser.isEnabled(), "Account should be enabled when active is true");
    }

    @Test
    void testAccountStatusInactiveReflectsPropertiesCorrectly() {
        User inactiveUser = new User(1L, "Denise", "User", "pass123", "deniseuser", "CUSTOMER", false);
        assertFalse(inactiveUser.isAccountNonExpired(), "Account should be expired when active is false");
        assertFalse(inactiveUser.isAccountNonLocked(), "Account should be locked when active is false");
        assertFalse(inactiveUser.isCredentialsNonExpired(), "Credentials should be expired when active is false");
        assertFalse(inactiveUser.isEnabled(), "Account should be disabled when active is false");
    }

    @Test
    public void testGetters() {
        User user = new User(1L, "John", "Doe", "secure123", "johndoe", "ADMIN", true);

        assertEquals(1L, user.getId(), "The id should match the one set in the constructor");
        assertEquals("John", user.getFirstname(), "The firstname should match the one set in the constructor");
        assertEquals("Doe", user.getLastname(), "The lastname should match the one set in the constructor");
        assertEquals("secure123", user.getPassword(), "The password should match the one set in the constructor");
        assertEquals("johndoe", user.getUsername(), "The username should match the one set in the constructor");
        assertEquals("ADMIN", user.getRole(), "The role should match the one set in the constructor");
        assertTrue(user.isActive(), "The isActive should match the one set in the constructor");
    }
}