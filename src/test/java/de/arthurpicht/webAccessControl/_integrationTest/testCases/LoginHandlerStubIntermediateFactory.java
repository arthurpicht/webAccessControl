package de.arthurpicht.webAccessControl._integrationTest.testCases;

import de.arthurpicht.webAccessControl.handler.LoginHandler;
import de.arthurpicht.webAccessControl.handler.LoginHandlerAbstractFactory;

public class LoginHandlerStubIntermediateFactory extends LoginHandlerAbstractFactory {

    @Override
    public LoginHandler create() {
        return new LoginHandlerStubIntermediate();
    }

}
