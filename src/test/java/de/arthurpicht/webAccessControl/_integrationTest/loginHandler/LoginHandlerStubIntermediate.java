package de.arthurpicht.webAccessControl._integrationTest.loginHandler;

import de.arthurpicht.webAccessControl._integrationTest.roles.RoleA;
import de.arthurpicht.webAccessControl.auth.UnauthorizedException;
import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.securityAttribute.User;
import de.arthurpicht.webAccessControl.securityAttribute.requirements.Requirements;

public class LoginHandlerStubIntermediate extends LoginHandler {

    @Override
    public SecurityAttribute checkCredentials(String username, char[] password) throws UnauthorizedException {
        User user = new RoleA("test-user-1");
        Requirements requirements = new Requirements();
        requirements.add2FARequirement();
        requirements.addPolicyConsentRequirement();

        return new SecurityAttribute(user, requirements);
    }

    @Override
    public SecurityAttribute checkCredentials(String accessKey) throws UnauthorizedException {
        throw new RuntimeException("Intentionally not implemented.");
    }

}
