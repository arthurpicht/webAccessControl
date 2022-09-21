package de.arthurpicht.webAccessControl.sessionManager;

import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;

import javax.servlet.http.HttpServletRequest;

public abstract class SessionManager {

    public abstract void createSession(HttpServletRequest httpServletRequest, SecurityAttribute securityAttribute);

    public abstract boolean hasSessionInAnyStaging(HttpServletRequest httpServletRequest);

    public abstract boolean hasValidSession(HttpServletRequest httpServletRequest);

    public abstract boolean hasIntermediateSession(HttpServletRequest httpServletRequest);

    public abstract void invalidate(HttpServletRequest httpServletRequest);

    public abstract SecurityAttribute getSecurityAttribute(HttpServletRequest httpServletRequest);

    public abstract String getSessionId(HttpServletRequest httpServletRequest);

}
