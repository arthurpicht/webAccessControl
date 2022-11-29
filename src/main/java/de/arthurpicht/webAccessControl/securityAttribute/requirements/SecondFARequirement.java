package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import java.io.Serializable;

public final class SecondFARequirement extends Requirement implements Serializable {

    private static final long serialVersionUID = -6663575012357743018L;

    public SecondFARequirement() {
        super("2FA");
    }

}
