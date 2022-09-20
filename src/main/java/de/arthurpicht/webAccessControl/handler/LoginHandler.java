package de.arthurpicht.webAccessControl.handler;

import de.arthurpicht.webAccessControl.auth.UnauthorizedException;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;

public abstract class LoginHandler {

    public abstract SecurityAttribute checkCredentials(String username, char[] password) throws UnauthorizedException;

    public abstract SecurityAttribute checkCredentials(String accessKey) throws UnauthorizedException;


}
