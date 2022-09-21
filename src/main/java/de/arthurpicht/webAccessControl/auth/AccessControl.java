package de.arthurpicht.webAccessControl.auth;

import de.arthurpicht.utils.core.strings.Strings;
import de.arthurpicht.webAccessControl.WACContext;
import de.arthurpicht.webAccessControl.WACContextRegistry;
import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.securityAttribute.User;
import de.arthurpicht.webAccessControl.sessionManager.SessionManager;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class AccessControl {

    private static final WACContext WAC_CONTEXT = WACContextRegistry.getContext();
    private static final Logger authLogger = WAC_CONTEXT.getLogger();
    private static final SessionManager sessionManager = WAC_CONTEXT.getSessionManager();

    public static void login(HttpServletRequest httpServletRequest, String username, char[] password)
            throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);
        assertArgumentNotNull("username", username);
        assertArgumentNotNull("password", password);

        sessionManager.invalidate(httpServletRequest);

        LoginHandler loginHandler = WAC_CONTEXT.createLoginHandler();
        SecurityAttribute securityAttribute = loginHandler.checkCredentials(username, password);

        sessionManager.createSession(httpServletRequest, securityAttribute);

        authLogger.info("user [" + username + "] login. ");
    }

    public static void loginWithAccessKey(HttpServletRequest httpServletRequest, String accessKey) throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);
        assertArgumentNotNull("accessKey", accessKey);

        sessionManager.invalidate(httpServletRequest);

        LoginHandler loginHandler = WAC_CONTEXT.createLoginHandler();
        SecurityAttribute securityAttribute = loginHandler.checkCredentials(accessKey);

        sessionManager.createSession(httpServletRequest, securityAttribute);

        authLogger.info("accessKey [" + accessKey + "] login. ");
    }

    public static void logout(HttpServletRequest httpServletRequest) {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);

        if (sessionManager.hasSessionInAnyStaging(httpServletRequest)) {
            SecurityAttribute securityAttribute = sessionManager.getSecurityAttribute(httpServletRequest);
            User user = securityAttribute.getUser();
            sessionManager.invalidate(httpServletRequest);
            authLogger.info("[" + user.getUserId() + "] logout");
        } else {
            authLogger.debug("Logout without running session.");
        }
    }

    public static Authorization assertValidSession(String roleName, HttpServletRequest httpServletRequest) throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);

        Authorization authorization = new Authorization(httpServletRequest);
        if (!authorization.isValid())
            throw new UnauthorizedException("Session not valid.");
        if (!authorization.isInRole(roleName))
            throw new UnauthorizedException("User not in role [" + roleName + "].");
        return authorization;
    }

    public static Authorization assertValidSession(Set<String> roleNames, HttpServletRequest httpServletRequest) throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);

        Authorization authorization = new Authorization(httpServletRequest);
        if (!authorization.isValid())
            throw new UnauthorizedException("Session not valid.");
        if (!roleNames.contains(authorization.getRoleName()))
            throw new UnauthorizedException("User not in one of that roles: "
                    + Strings.listing(roleNames, ",", "[", "]") + ".");
        return authorization;
    }

    public static Authorization assertValidSessionForAnyRole(HttpServletRequest httpServletRequest) throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);

        Authorization authorization = new Authorization(httpServletRequest);
        if (!authorization.isValid())
            throw new UnauthorizedException("Session not valid.");
        return authorization;
    }

    public static Authorization assertHasSessionInAnyStaging(HttpServletRequest httpServletRequest) throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);

        return new Authorization(httpServletRequest);
    }

    public static Authorization assertIntermediateSession(HttpServletRequest httpServletRequest) throws UnauthorizedException {
        assertArgumentNotNull("httpServletRequest", httpServletRequest);

        Authorization authorization = new Authorization(httpServletRequest);
        if (!authorization.isIntermediate())
            throw new UnauthorizedException("Session not intermediate.");
        return authorization;
    }

}
