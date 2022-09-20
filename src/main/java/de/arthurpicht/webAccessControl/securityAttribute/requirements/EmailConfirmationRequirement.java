package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import java.io.Serializable;

public class EmailConfirmationRequirement implements Requirement, Serializable {

    private static final long serialVersionUID = -8907803714615755199L;
    private final String token;
    private final String code;

    public EmailConfirmationRequirement(String token, String code) {
        this.token = token;
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return "EMAIL_CONFIRMATION";
    }

}
