package de.arthurpicht.webAccessControl._integrationTest.loginHandler;

import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.handler.LoginHandlerAbstractFactory;

public class LoginHandlerStubValidFactory extends LoginHandlerAbstractFactory {

    @Override
    public LoginHandler create() {
        return new LoginHandlerStubValid();
    }

}
