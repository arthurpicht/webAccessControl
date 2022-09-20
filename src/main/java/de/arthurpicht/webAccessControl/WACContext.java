package de.arthurpicht.webAccessControl;

import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.handler.LoginHandlerAbstractFactory;
import de.arthurpicht.webAccessControl.handler.RoleRegistry;
import de.arthurpicht.webAccessControl.sessionManager.SessionManager;
import de.arthurpicht.webAccessControl.sessionManager.SessionManagerAbstractFactory;
import org.slf4j.Logger;

public class WACContext {

    private final Logger logger;
    private final RoleRegistry roleRegistry;
    private final LoginHandlerAbstractFactory loginHandlerAbstractFactory;
    private final SessionManagerAbstractFactory sessionManagerAbstractFactory;

    public WACContext(
            Logger logger,
            RoleRegistry roleRegistry,
            LoginHandlerAbstractFactory loginHandlerAbstractFactory,
            SessionManagerAbstractFactory sessionManagerAbstractFactory) {
        this.logger = logger;
        this.roleRegistry = roleRegistry;
        this.loginHandlerAbstractFactory = loginHandlerAbstractFactory;
        this.sessionManagerAbstractFactory = sessionManagerAbstractFactory;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public RoleRegistry getRoleRegistry() {
        return this.roleRegistry;
    }

    public LoginHandler createLoginHandler() {
        return this.loginHandlerAbstractFactory.create();
    }

    public SessionManager getSessionManager() {
        return this.sessionManagerAbstractFactory.create();
    }

}
