package de.arthurpicht.webAccessControl.securityAttribute;

import de.arthurpicht.webAccessControl.securityAttribute.requirements.Requirements;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StagingTest {

    @Test
    public void isValid() {
        Requirements requirements = new Requirements();
        Staging staging = new Staging(requirements);

        assertTrue(staging.isValid());
        assertFalse(staging.isIntermediate());
        assertEquals("VALID", staging.toString());
    }

    @Test
    public void isIntermediate() {
        Requirements requirements = new Requirements();
        requirements.add2FARequirement();
        Staging staging = new Staging(requirements);

        assertFalse(staging.isValid());
        assertTrue(staging.isIntermediate());
        assertEquals("INTERMEDIATE Requirements: 2FA.", staging.toString());
    }

}