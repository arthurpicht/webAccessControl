package de.arthurpicht.webAccessControl.authorization;

import de.arthurpicht.webAccessControl.WACContext;
import de.arthurpicht.webAccessControl.WACContextRegistry;
import de.arthurpicht.webAccessControl._integrationTest.globalStubs.HttpServletRequestStub;
import de.arthurpicht.webAccessControl._integrationTest.globalStubs.SessionManagerStubAbstractFactory;
import de.arthurpicht.webAccessControl._integrationTest.roles.RoleA;
import de.arthurpicht.webAccessControl._integrationTest.roles.RoleB;
import de.arthurpicht.webAccessControl._integrationTest.loginHandler.LoginHandlerStubValidFactory;
import de.arthurpicht.webAccessControl.auth.Authorization;
import de.arthurpicht.webAccessControl.auth.UnauthorizedException;
import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.handler.RoleRegistry;
import de.arthurpicht.webAccessControl.securityAttribute.SecurityAttribute;
import de.arthurpicht.webAccessControl.sessionManager.SessionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationValidTest {

    private static Authorization authorization;

    @BeforeAll
    public static void init() throws UnauthorizedException {
        WACContextRegistry.reset();
        Logger logger = LoggerFactory.getLogger("IntegrationTest");
        RoleRegistry roleRegistry = new RoleRegistry.Builder()
                .add(RoleA.ROLE_NAME, RoleA.class)
                .add(RoleB.ROLE_NAME, RoleB.class)
                .build();

        WACContext context = new WACContext(
                logger,
                roleRegistry,
                new LoginHandlerStubValidFactory(),
                new SessionManagerStubAbstractFactory()
        );

        WACContextRegistry.initialize(context);

        SessionManager sessionManager = WACContextRegistry.getContext().getSessionManager();
        LoginHandler loginHandler = WACContextRegistry.getContext().createLoginHandler();

        HttpServletRequest httpServletRequest = new HttpServletRequestStub("test-user-1");
        SecurityAttribute securityAttribute = loginHandler.checkCredentials("user1", "secret".toCharArray());
        sessionManager.createSession(httpServletRequest, securityAttribute);

        authorization = new Authorization(httpServletRequest);
    }

    @Test
    public void getUserId() {
        assertEquals("test-user-1", authorization.getUserId());
    }

    @Test
    public void getSessionId() {
        assertEquals("test-user-1_session" , authorization.getSessionId());
    }

    @Test
    public void getNextRequirement() {
        Exception e = assertThrows(IllegalStateException.class, () -> authorization.getNextRequirement());
        assertEquals("Wrong staging: [INTERMEDIATE] is expected.", e.getMessage());
    }

    @Test
    public void isInRole() {
        assertTrue(authorization.isInRole("ROLE_A"));
        assertFalse(authorization.isInRole("ROLE_B"));

        Exception e = assertThrows(IllegalArgumentException.class, () -> authorization.isInRole("not_registered_role"));
        assertEquals("No role registered for name [not_registered_role].", e.getMessage());
    }

    @Test
    public void isValid() {
        assertTrue(authorization.isValid());
    }

    @Test
    public void isIntermediate() {
        assertFalse(authorization.isIntermediate());
    }

    @Test
    public void getRoleName() {
        assertEquals("ROLE_A", authorization.getRoleName());
    }

}
