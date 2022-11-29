package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import java.io.Serializable;

public final class EmailConfirmationRequirement extends Requirement implements Serializable {

    private static final long serialVersionUID = -8907803714615755199L;
    private final String token;
    private final String code;

    public EmailConfirmationRequirement(String token, String code) {
        super("EMAIL_CONFIRMATION");
        this.token = token;
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public String getCode() {
        return code;
    }

}
