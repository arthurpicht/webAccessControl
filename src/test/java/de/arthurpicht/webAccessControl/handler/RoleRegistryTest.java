package de.arthurpicht.webAccessControl.handler;

import de.arthurpicht.webAccessControl.securityAttribute.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleRegistryTest {

    private static class UserInRoleA extends User {
        @Override
        public String getRoleName() {
            return "ROLE_A";
        }

        @Override
        public String getUserId() {
            return "testUser_role_A";
        }
    }

    private static class UserInRoleB extends User {
        @Override
        public String getRoleName() {
            return "ROLE_B";
        }

        @Override
        public String getUserId() {
            return "testUser_role_B";
        }
    }

    private static class UserInRoleNN extends User {
        @Override
        public String getRoleName() {
            return "ROLE_NN";
        }

        @Override
        public String getUserId() {
            return "testUser_role_NN";
        }
    }


    private static RoleRegistry roleRegistry;

    @BeforeAll
    public static void setup() {
        roleRegistry = new RoleRegistry.Builder()
                .add("ROLE_A", UserInRoleA.class)
                .add("ROLE_B", UserInRoleB.class)
                .build();
    }

    @Test
    public void hasRole() {
        assertTrue(roleRegistry.hasRole("ROLE_A"));
        assertFalse(roleRegistry.hasRole("NONE"));
    }

    @Test
    public void getRole() {
        Class<? extends User> role = roleRegistry.getRole("ROLE_B");
        assertEquals(UserInRoleB.class, role);
    }

    @Test
    public void getRole_neg() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> roleRegistry.getRole("ROLE_NN"));
        assertEquals("Role with name [ROLE_NN] not contained in RoleRegistry.", e.getMessage());
    }

    @Test
    public void getRoleName() {
        String roleName = roleRegistry.getRoleName(UserInRoleA.class);
        assertEquals("ROLE_A", roleName);
    }

    @Test
    public void getRoleName_neg() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> roleRegistry.getRoleName(UserInRoleNN.class));
        assertEquals(
                "Role class [de.arthurpicht.webAccessControl.handler.RoleRegistryTest.UserInRoleNN] not" +
                        " contained in RoleRegistry.",
                e.getMessage()
        );
    }



}