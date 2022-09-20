package de.arthurpicht.webAccessControl.sessionManager;

public class SessionManagerFactoryHttp extends SessionManagerAbstractFactory {

    @Override
    public SessionManager create() {
        return new SessionManagerHttp();
    }

}
