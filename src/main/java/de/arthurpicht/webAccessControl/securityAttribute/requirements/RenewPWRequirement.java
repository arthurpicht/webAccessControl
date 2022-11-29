package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import java.io.Serializable;

public final class RenewPWRequirement extends Requirement implements Serializable {

    private static final long serialVersionUID = 6297658560168555958L;

    public RenewPWRequirement() {
        super("RENEW_PASSWORD");
    }

}
