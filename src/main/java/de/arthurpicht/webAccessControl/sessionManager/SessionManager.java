package de.arthurpicht.webAccessControl.sessionManager;

import de.arthurpicht.webAccessControl.WACContextRegistry;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

public abstract class SessionManager {

    protected static final Logger authLogger = WACContextRegistry.getContext().getLogger();

    public abstract void createSession(HttpServletRequest httpServletRequest, SecurityAttribute securityAttribute);

    public abstract boolean hasSessionInAnyStaging(HttpServletRequest httpServletRequest);

    public abstract boolean hasValidSession(HttpServletRequest httpServletRequest);

    public abstract boolean hasIntermediateSession(HttpServletRequest httpServletRequest);

    public abstract void invalidate(HttpServletRequest httpServletRequest);

    public abstract void invalidatePreexisting(HttpServletRequest httpServletRequest);

    public abstract SecurityAttribute getSecurityAttribute(HttpServletRequest httpServletRequest);

    public abstract String getSessionId(HttpServletRequest httpServletRequest);

}
