package de.arthurpicht.webAccessControl.securityAttribute;

import java.io.Serializable;

public abstract class User implements Serializable {

    private static final long serialVersionUID = 2611934872368841337L;

    public abstract String getUserId();

}
