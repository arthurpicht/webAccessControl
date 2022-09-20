package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementsTest {

    @Test
    public void hasRequirements() {
        Requirements requirements = new Requirements();
        assertFalse(requirements.hasRequirements());
        requirements.add2FARequirement();
        assertTrue(requirements.hasRequirements());
    }

    @Test
    public void testToStringWithEmptyRequirements() {
        Requirements requirements = new Requirements();
        assertEquals("Requirements: none.", requirements.toString());
    }

    @Test
    public void testToStringWithRequirements() {
        Requirements requirements = new Requirements();
        requirements.add2FARequirement();
        requirements.addPolicyConsentRequirement();
        assertEquals("Requirements: 2FA, POLICY_CONSENT.", requirements.toString());
    }

    @Test
    public void getNextRequirementFail() {
        Requirements requirements = new Requirements();
        IllegalStateException e = assertThrows(IllegalStateException.class, requirements::getNextRequirement);
        assertEquals("No next requirement.", e.getMessage());
    }

    @Test
    public void getNextRequirement_markNextRequirementAsFulfilled() {
        Requirements requirements = new Requirements();
        requirements.add2FARequirement();
        requirements.addPolicyConsentRequirement();

        assertTrue(requirements.hasRequirements());

        Requirement nextRequirement = requirements.getNextRequirement();
        assertTrue(nextRequirement instanceof SecondFARequirement);

        requirements.markNextRequirementAsFulfilled();
        assertTrue(requirements.hasRequirements());

        nextRequirement = requirements.getNextRequirement();
        assertTrue(nextRequirement instanceof PolicyConsentRequirement);

        requirements.markNextRequirementAsFulfilled();
        assertFalse(requirements.hasRequirements());
    }

    @Test
    public void addRequirement_custom() {
        Requirements requirements = new Requirements();
        requirements.addRequirement(() -> "TEST_REQUIREMENT");

        assertTrue(requirements.hasRequirements());
        Requirement requirement = requirements.getNextRequirement();
        assertEquals("TEST_REQUIREMENT", requirement.getName());
    }

}