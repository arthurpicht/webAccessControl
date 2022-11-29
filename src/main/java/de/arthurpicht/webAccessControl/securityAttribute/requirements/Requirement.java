package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import de.arthurpicht.utils.core.assertion.MethodPreconditions;

import java.io.Serializable;
import java.util.Objects;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNullAndNotEmpty;

public abstract class Requirement implements Serializable {

    private static final long serialVersionUID = 5079247255409831374L;
    protected final String name;

    public Requirement(String name) {
        assertArgumentNotNullAndNotEmpty("name", name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requirement that = (Requirement) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
}
