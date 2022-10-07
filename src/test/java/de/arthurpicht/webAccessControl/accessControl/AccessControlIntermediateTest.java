package de.arthurpicht.webAccessControl.accessControl;

import de.arthurpicht.utils.core.collection.Sets;
import de.arthurpicht.webAccessControl.WACContext;
import de.arthurpicht.webAccessControl.WACContextRegistry;
import de.arthurpicht.webAccessControl._integrationTest.globalStubs.HttpServletRequestStub;
import de.arthurpicht.webAccessControl._integrationTest.globalStubs.SessionManagerStubAbstractFactory;
import de.arthurpicht.webAccessControl._integrationTest.loginHandler.LoginHandlerStubIntermediateFactory;
import de.arthurpicht.webAccessControl._integrationTest.roles.RoleA;
import de.arthurpicht.webAccessControl._integrationTest.roles.RoleB;
import de.arthurpicht.webAccessControl.auth.AccessControl;
import de.arthurpicht.webAccessControl.auth.Authorization;
import de.arthurpicht.webAccessControl.auth.UnauthorizedException;
import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.handler.RoleRegistry;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.sessionManager.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

public class AccessControlIntermediateTest {

    @BeforeAll
    public static void init() {
        Logger logger = LoggerFactory.getLogger("IntegrationTest");
        RoleRegistry roleRegistry = new RoleRegistry.Builder()
                .add(RoleA.ROLE_NAME, RoleA.class)
                .add(RoleB.ROLE_NAME, RoleB.class)
                .build();

        WACContext context = new WACContext(
                logger,
                roleRegistry,
                new LoginHandlerStubIntermediateFactory(),
                new SessionManagerStubAbstractFactory()
        );

        WACContextRegistry.initialize(context);
    }

    @Test
    public void fullCycle() throws UnauthorizedException {
        SessionManager sessionManager = WACContextRegistry.getContext().getSessionManager();
        HttpServletRequest httpServletRequest = new HttpServletRequestStub("test-user-1");
        assertFalse(sessionManager.hasSessionInAnyStaging(httpServletRequest));

        AccessControl.login(httpServletRequest, "user1", "secret".toCharArray());
        assertTrue(sessionManager.hasSessionInAnyStaging(httpServletRequest));

        AccessControl.assertHasSessionInAnyStaging(httpServletRequest);

        AccessControl.assertIntermediateSession(httpServletRequest);

        assertThrows(UnauthorizedException.class,
                () -> AccessControl.assertValidSessionForAnyRole(httpServletRequest));

        assertThrows(UnauthorizedException.class,
                () -> AccessControl.assertValidSession(RoleA.ROLE_NAME, httpServletRequest));

        assertThrows(UnauthorizedException.class,
                () -> AccessControl.assertValidSession(Sets.newHashSet(RoleA.ROLE_NAME, RoleB.ROLE_NAME), httpServletRequest));

        AccessControl.logout(httpServletRequest);
        assertFalse(sessionManager.hasSessionInAnyStaging(httpServletRequest));
    }

}

