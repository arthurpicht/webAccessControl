package de.arthurpicht.webAccessControl._integrationTest.globalStubs;

import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.sessionManager.SessionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SessionManagerStub extends SessionManager {

    private static final Map<String, SecurityAttribute> securityAttributeMap = new HashMap<>();

    @Override
    public void createSession(HttpServletRequest httpServletRequest, SecurityAttribute securityAttribute) {
        HttpServletRequestStub httpServletRequestStub = cast(httpServletRequest);
        String userId = httpServletRequestStub.getUserId();
        if (!userId.equals(securityAttribute.getUser().getUserId()))
            throw new IllegalStateException("User-IDs of " + HttpServletRequestStub.class.getSimpleName() + " and "
                    + SecurityAttribute.class.getSimpleName() + " do not match.");
        securityAttributeMap.put(userId, securityAttribute);
    }

    @Override
    public void updateSession(HttpServletRequest httpServletRequest, SecurityAttribute securityAttribute) {
        HttpServletRequestStub httpServletRequestStub = cast(httpServletRequest);
        String userId = httpServletRequestStub.getUserId();
        if (!hasSessionInAnyStaging(httpServletRequest))
            throw new IllegalStateException("No running session for user: " + userId);
        securityAttributeMap.put(userId, securityAttribute);
    }

    @Override
    public boolean hasSessionInAnyStaging(HttpServletRequest httpServletRequest) {
        HttpServletRequestStub httpServletRequestStub = cast(httpServletRequest);
        return securityAttributeMap.containsKey(httpServletRequestStub.getUserId());
    }

    @Override
    public boolean hasValidSession(HttpServletRequest httpServletRequest) {
        SecurityAttribute securityAttribute = getSecurityAttribute(httpServletRequest);
        return securityAttribute.isValid();
    }

    @Override
    public boolean hasIntermediateSession(HttpServletRequest httpServletRequest) {
        SecurityAttribute securityAttribute = getSecurityAttribute(httpServletRequest);
        return securityAttribute.isIntermediate();
    }

    @Override
    public void invalidate(HttpServletRequest httpServletRequest) {
        HttpServletRequestStub httpServletRequestStub = cast(httpServletRequest);
        String userId = httpServletRequestStub.getUserId();
        securityAttributeMap.remove(userId);
    }

    @Override
    public SecurityAttribute getSecurityAttribute(HttpServletRequest httpServletRequest) {
        HttpServletRequestStub httpServletRequestStub = cast(httpServletRequest);
        String userId = httpServletRequestStub.getUserId();
        if (!securityAttributeMap.containsKey(userId))
            throw new IllegalStateException("No running session for user: " + userId);
        return securityAttributeMap.get(userId);
    }

    @Override
    public String getSessionId(HttpServletRequest httpServletRequest) {
        HttpServletRequestStub httpServletRequestStub = cast(httpServletRequest);
        String userId = httpServletRequestStub.getUserId();
        if (!securityAttributeMap.containsKey(userId))
            throw new IllegalStateException("No running session for user: " + userId);
        return userId + "_session";
    }

    private HttpServletRequestStub cast(HttpServletRequest httpServletRequest) {
        if (!(httpServletRequest instanceof HttpServletRequestStub))
            throw new IllegalArgumentException("Not an instance of " + HttpServletRequestStub.class.getCanonicalName());
        return (HttpServletRequestStub) httpServletRequest;
    }

}
