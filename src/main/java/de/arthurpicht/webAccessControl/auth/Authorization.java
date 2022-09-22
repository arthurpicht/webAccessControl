package de.arthurpicht.webAccessControl.auth;

import de.arthurpicht.webAccessControl.WACContextRegistry;
import de.arthurpicht.webAccessControl.handler.RoleRegistry;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.securityAttribute.User;
import de.arthurpicht.webAccessControl.securityAttribute.requirements.Requirement;
import de.arthurpicht.webAccessControl.sessionManager.SessionManager;

import javax.servlet.http.HttpServletRequest;

public class Authorization {

    protected static final RoleRegistry roleRegistry = WACContextRegistry.getContext().getRoleRegistry();
    protected static final SessionManager sessionManager = WACContextRegistry.getContext().getSessionManager();

    protected String sessionId;
    protected SecurityAttribute securityAttribute;

    public Authorization(HttpServletRequest httpServletRequest) throws UnauthorizedException {
        if (!sessionManager.hasSessionInAnyStaging(httpServletRequest))
            throw new UnauthorizedException("[Authentication] failed: No running session.");

        this.sessionId = sessionManager.getSessionId(httpServletRequest);
        this.securityAttribute = sessionManager.getSecurityAttribute(httpServletRequest);
    }

    public String getUserId() {
        return this.securityAttribute.getUser().getUserId();
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public boolean isValid() {
        return this.securityAttribute.isValid();
    }

    public boolean isIntermediate() {
        return this.securityAttribute.isIntermediate();
    }

    public User getUser() {
        return this.securityAttribute.getUser();
    }

    public Requirement getNextRequirement() {
        if (!isIntermediate()) throw new IllegalStateException("Wrong staging: [INTERMEDIATE] is expected.");
        return this.securityAttribute.getStaging().getRequirements().getNextRequirement();
    }

    public boolean isInRole(String roleName) {
        if (!roleRegistry.hasRole(roleName))
            throw new IllegalArgumentException("No role registered for name [" + roleName + "].");

        Class<? extends User> registeredRole = roleRegistry.getRole(roleName);
        return isInRole(registeredRole);
    }

    public boolean isInRole(Class<? extends User> userClass) {
        return this.securityAttribute.getUser().getClass().getCanonicalName().equals(userClass.getCanonicalName());
    }

    public SecurityAttribute getSecurityAttribute() {
        return this.securityAttribute;
    }

    public String getRoleName() {
        Class<? extends User>  role = this.securityAttribute.getRole();
        return roleRegistry.getRoleName(role);
    }

}
