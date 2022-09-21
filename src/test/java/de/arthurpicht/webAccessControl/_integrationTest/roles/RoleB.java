package de.arthurpicht.webAccessControl._integrationTest.roles;

import de.arthurpicht.webAccessControl.securityAttribute.User;

public class RoleB extends User {

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
