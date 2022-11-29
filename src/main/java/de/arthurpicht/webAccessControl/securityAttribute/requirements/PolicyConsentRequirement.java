package de.arthurpicht.webAccessControl.securityAttribute.requirements;


import java.io.Serializable;

public final class PolicyConsentRequirement extends Requirement implements Serializable {

    private static final long serialVersionUID = -3191800083444799309L;

    public PolicyConsentRequirement() {
        super("POLICY_CONSENT");
    }

}
