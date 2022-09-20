package de.arthurpicht.webAccessControl.handler;

import de.arthurpicht.webAccessControl.securityAttribute.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleRegistryTest {

    private static class UserInRoleA extends User {
        @Override
        public String getUserId() {
            return "testUser_role_A";
        }
    }

    private static class UserInRoleB extends User {
        @Override
        public String getUserId() {
            return "testUser_role_B";
        }
    }

    @Test
    public void simple() {

        RoleRegistry roleRegistry = new RoleRegistry.Builder()
                .add("ROLE_A", UserInRoleA.class)
                .add("ROLE_B", UserInRoleB.class)
                .build();

        assertTrue(roleRegistry.hasRoleByName("ROLE_A"));
        assertFalse(roleRegistry.hasRoleByName("NONE"));


    }

}