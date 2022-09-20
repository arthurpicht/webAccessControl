package de.arthurpicht.webAccessControl;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class WACContextRegistry {

    private static WACContext WACContext = null;

    public static void initialize(WACContext WACContext) {
        assertArgumentNotNull("context", WACContext);
        WACContextRegistry.WACContext = WACContext;
    }

    public static WACContext getContext() {
        assertInitialized();
        return WACContext;
    }

    private static void assertInitialized() {
        if (WACContext == null) throw new IllegalStateException(WACContextRegistry.class.getName() + " not initialized.");
    }

}
