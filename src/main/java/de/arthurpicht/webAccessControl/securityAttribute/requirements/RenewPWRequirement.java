package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import java.io.Serializable;

public class RenewPWRequirement implements Requirement, Serializable {

    private static final long serialVersionUID = 6297658560168555958L;

    @Override
    public String getName() {
        return "RENEW_PASSWORD";
    }
}
