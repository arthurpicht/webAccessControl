package de.arthurpicht.webAccessControl._integrationTest.roles;

import de.arthurpicht.webAccessControl.securityAttribute.User;

import java.io.Serializable;

public class RoleB extends User implements Serializable {

    public static final String ROLE_NAME = "ROLE_B";

    private final String userId;

    public RoleB(String userId) {
        this.userId = userId;
    }

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }

    @Override
    public String getUserId() {
        return userId;
    }

}
