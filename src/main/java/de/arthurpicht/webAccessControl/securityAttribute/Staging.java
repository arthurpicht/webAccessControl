package de.arthurpicht.webAccessControl.securityAttribute;

import de.arthurpicht.webAccessControl.securityAttribute.requirements.Requirements;

import java.io.Serializable;

public class Staging implements Serializable {

    public static final String VALID = "VALID";
    public static final String INTERMEDIATE = "INTERMEDIATE";
    public static final String INVALID = "INVALID";
    private static final long serialVersionUID = -6788188311116073225L;

    private final Requirements requirements;

    public Staging(Requirements requirements) {
        this.requirements = requirements;
    }

    public boolean isValid() {
        return !this.requirements.hasRequirements();
    }

    public boolean isIntermediate() {
        return this.requirements.hasRequirements();
    }

    public Requirements getRequirements() {
        return this.requirements;
    }

    @Override
    public String toString() {
        if (this.requirements.hasRequirements()) {
            return INTERMEDIATE + " " + this.requirements;
        }
        return VALID;
    }

}
