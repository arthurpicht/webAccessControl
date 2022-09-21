package de.arthurpicht.webAccessControl._integrationTest.globalStubs;

import de.arthurpicht.webAccessControl.sessionManager.SessionManager;
import de.arthurpicht.webAccessControl.sessionManager.SessionManagerAbstractFactory;

public class SessionManagerStubAbstractFactory extends SessionManagerAbstractFactory {

    @Override
    public SessionManager create() {
        return new SessionManagerStub();
    }

}
