package de.arthurpicht.webAccessControl.securityAttribute;

import java.io.Serializable;

public abstract class User implements Serializable {

    private static final long serialVersionUID = 2611934872368841337L;

    public abstract String getRoleName();

    public abstract String getUserId();

    @Override
    public int hashCode() {
        return getUserId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User that = (User) obj;
        return getUserId().equals(that.getUserId());
    }
    
}
