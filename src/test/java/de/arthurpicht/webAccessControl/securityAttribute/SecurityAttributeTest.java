package de.arthurpicht.webAccessControl.securityAttribute;

import de.arthurpicht.webAccessControl.securityAttribute.requirements.Requirements;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityAttributeTest {

    private static class TestUser extends User {

        @Override
        public String getRoleName() {
            return "TEST_USER_ROLE";
        }

        @Override
        public String getUserId() {
            return "TEST_USER_ID";
        }
    }

    @Test
    public void isValid() {
        TestUser testUser = new TestUser();
        Requirements requirements = new Requirements();

        SecurityAttribute securityAttribute = new SecurityAttribute(testUser,requirements);
        assertTrue(securityAttribute.isValid());
        assertFalse(securityAttribute.isIntermediate());
        assertEquals(
                TestUser.class,
                securityAttribute.getRole());
        assertTrue(securityAttribute.isInRole(TestUser.class));
        assertEquals(testUser, securityAttribute.getUser());
    }

    @Test
    public void isIntermediate() {
        TestUser testUser = new TestUser();
        Requirements requirements = new Requirements();
        requirements.add2FARequirement();

        SecurityAttribute securityAttribute = new SecurityAttribute(testUser,requirements);
        assertFalse(securityAttribute.isValid());
        assertTrue(securityAttribute.isIntermediate());
    }

}