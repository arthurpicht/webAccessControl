package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import java.io.Serializable;

public class SecondFARequirement implements Requirement, Serializable {

    private static final long serialVersionUID = -6663575012357743018L;

    @Override
    public String getName() {
        return "2FA";
    }

}
