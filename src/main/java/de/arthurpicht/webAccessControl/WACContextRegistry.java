package de.arthurpicht.webAccessControl;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class WACContextRegistry {

    private static WACContext WACContext = null;

    public static synchronized void initialize(WACContext WACContext) {
        assertArgumentNotNull("context", WACContext);
        if (WACContextRegistry.WACContext != null)
            throw new IllegalStateException(WACContext.class.getSimpleName() + " already initialized in "
                    + WACContextRegistry.class.getSimpleName() + ".");
        WACContextRegistry.WACContext = WACContext;
    }

    public static synchronized WACContext getContext() {
        assertInitialized();
        return WACContext;
    }

    /**
     * To be used in test cases only!
     */
    public static synchronized  void reset() {
        WACContext = null;
    }

    private static void assertInitialized() {
        if (WACContext == null) throw new IllegalStateException(WACContextRegistry.class.getName() + " not initialized.");
    }

}
