package de.arthurpicht.webAccessControl.sessionManager;

import de.arthurpicht.webAccessControl.helper.HttpSessions;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.securityAttribute.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManagerHttp extends SessionManager {

    public static final String ATTRIBUTE_NAME = "ap-webAccessControl";

    @Override
    public void createSession(HttpServletRequest httpServletRequest, SecurityAttribute securityAttribute) {
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute(ATTRIBUTE_NAME, securityAttribute);
    }

    @Override
    public boolean hasSessionInAnyStaging(HttpServletRequest httpServletRequest) {
        return HttpSessions.hasRunningSession(httpServletRequest);
    }

    @Override
    public boolean hasValidSession(HttpServletRequest httpServletRequest) {
        if (!HttpSessions.hasRunningSession(httpServletRequest)) return false;
        SecurityAttribute securityAttribute = getSecurityAttribute(httpServletRequest);
        return securityAttribute.isValid();
    }

    @Override
    public boolean hasIntermediateSession(HttpServletRequest httpServletRequest) {
        if (!HttpSessions.hasRunningSession(httpServletRequest)) return false;
        SecurityAttribute securityAttribute = getSecurityAttribute(httpServletRequest);
        return securityAttribute.isIntermediate();
    }

    @Override
    public void invalidate(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession != null) httpSession.invalidate();
    }

    @Override
    public SecurityAttribute getSecurityAttribute(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = HttpSessions.getRunningSession(httpServletRequest);
        SecurityAttribute securityAttribute = (SecurityAttribute) httpSession.getAttribute(ATTRIBUTE_NAME);
        if (securityAttribute == null)
            throw new IllegalStateException("No SecurityAttribute found in running session.");
        return securityAttribute;
    }

    @Override
    public String getSessionId(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = HttpSessions.getRunningSession(httpServletRequest);
        return httpSession.getId();
    }

}
