package de.arthurpicht.webAccessControl.securityAttribute.requirements;


import java.io.Serializable;

public class PolicyConsentRequirement implements Requirement, Serializable {

    private static final long serialVersionUID = -3191800083444799309L;

    @Override
    public String getName() {
        return "POLICY_CONSENT";
    }

}
