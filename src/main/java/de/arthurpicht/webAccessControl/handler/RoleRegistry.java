package de.arthurpicht.webAccessControl.handler;

import de.arthurpicht.utils.core.collection.Maps;

import java.util.HashMap;
import java.util.Map;

public final class RoleRegistry {

    private final Map<String, String> roleRegistryMap;

    public RoleRegistry(Map<String, String> roleRegistryMap) {
        this.roleRegistryMap = roleRegistryMap;
    }

    public boolean hasRoleByName(String roleName) {
        return this.roleRegistryMap.containsKey(roleName);
    }

    public String getClassName(String roleName) {
        return this.roleRegistryMap.get(roleName);
    }

    public String getRoleName(String className) {
        if (!this.roleRegistryMap.containsValue(className))
            throw new IllegalArgumentException("Role class [" + className + "] not contained in "
                    + RoleRegistry.class.getSimpleName());
        //noinspection OptionalGetWithoutIsPresent
        return this.roleRegistryMap
                .entrySet()
                .stream()
                .filter(entry -> className.equals(entry.getValue()))
                .findFirst()
                .get()
                .getValue();
    }

    public static class Builder {

        private final Map<String, String> roleRegistryMap;

        public Builder() {
            this.roleRegistryMap = new HashMap<>();
        }

        public Builder add(String roleName, Class<?> roleClass) {
            if (roleRegistryMap.containsKey(roleName)) {
                throw new IllegalArgumentException(
                        "Role name [" + roleName + "] is already contained by "
                                + RoleRegistry.class.getSimpleName() + ".");
            }
            String className = roleClass.getCanonicalName();
            if (roleRegistryMap.containsValue(className))
                throw new IllegalArgumentException(
                        "Role class [" + className + "] is already contained by "
                                + RoleRegistry.class.getSimpleName() + ".");

            this.roleRegistryMap.put(roleName, className);

            return this;
        }

        public RoleRegistry build() {
            return new RoleRegistry(Maps.immutableMap(this.roleRegistryMap));
        }

    }

}
