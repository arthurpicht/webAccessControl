package de.arthurpicht.webAccessControl._integrationTest.roles;

import de.arthurpicht.webAccessControl.securityAttribute.User;

import java.io.Serializable;

public class RoleA extends User implements Serializable {

    public static final String ROLE_NAME = "ROLE_A";

    private final String userId;

    public RoleA(String userId) {
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
