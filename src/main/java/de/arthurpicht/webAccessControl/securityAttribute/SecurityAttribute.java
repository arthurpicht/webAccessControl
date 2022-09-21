package de.arthurpicht.webAccessControl.securityAttribute;

import de.arthurpicht.webAccessControl.securityAttribute.requirements.Requirements;

import java.io.Serializable;
import java.util.Objects;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class SecurityAttribute implements Serializable {

    private static final long serialVersionUID = 5244402091820196405L;

    private final User user;
    private final Staging staging;

    public SecurityAttribute(
            User user,
            Requirements requirements
    ) {
        assertArgumentNotNull("user", user);
        assertArgumentNotNull("requirements", requirements);
        this.user = user;
        this.staging = new Staging(requirements);
    }

    public User getUser() {
        return this.user;
    }

    public Staging getStaging() {
        return this.staging;
    }

    public boolean isValid() {
        return this.staging.isValid();
    }

    public boolean isIntermediate() {
        return this.staging.isIntermediate();
    }

    public Class<? extends User> getRole() {
        return this.user.getClass();
    }

    public boolean isInRole(Class<? extends User> role) {
        return this.user.getClass().getCanonicalName().equals(role.getCanonicalName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityAttribute that = (SecurityAttribute) o;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
