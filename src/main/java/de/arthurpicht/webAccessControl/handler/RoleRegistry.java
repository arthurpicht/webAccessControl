package de.arthurpicht.webAccessControl.handler;

import de.arthurpicht.utils.core.collection.Maps;
import de.arthurpicht.webAccessControl.securityAttribute.User;

import java.util.HashMap;
import java.util.Map;

public final class RoleRegistry {

    private final Map<String, Class<? extends User>> roleRegistryMap;

    public RoleRegistry(Map<String, Class<? extends User>> roleRegistryMap) {
        this.roleRegistryMap = roleRegistryMap;
    }

    public boolean hasRole(String roleName) {
        return this.roleRegistryMap.containsKey(roleName);
    }

    public Class<? extends User> getRole(String roleName) {
        if (!this.roleRegistryMap.containsKey(roleName))
            throw new IllegalArgumentException("Role with name [" + roleName + "] not contained in "
                    + RoleRegistry.class.getSimpleName() + ".");

        return this.roleRegistryMap.get(roleName);
    }

    public String getRoleName(Class<? extends User> roleClass) {
        if (!this.roleRegistryMap.containsValue(roleClass))
            throw new IllegalArgumentException("Role class [" + roleClass.getCanonicalName() + "] not contained in "
                    + RoleRegistry.class.getSimpleName() + ".");
        //noinspection OptionalGetWithoutIsPresent
        return this.roleRegistryMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getCanonicalName().equals(roleClass.getCanonicalName()))
                .findFirst()
                .get()
                .getKey();
    }

    public static class Builder {

        private final Map<String, Class<? extends User>> roleRegistryMap;

        public Builder() {
            this.roleRegistryMap = new HashMap<>();
        }

        public Builder add(String roleName, Class<? extends User> roleClass) {
            if (roleRegistryMap.containsKey(roleName)) {
                throw new IllegalArgumentException(
                        "Role name [" + roleName + "] is already contained by "
                                + RoleRegistry.class.getSimpleName() + ".");
            }
            if (roleRegistryMap.containsValue(roleClass))
                throw new IllegalArgumentException(
                        "Role class [" + roleClass.getCanonicalName() + "] is already contained by "
                                + RoleRegistry.class.getSimpleName() + ".");

            this.roleRegistryMap.put(roleName, roleClass);

            return this;
        }

        public RoleRegistry build() {
            return new RoleRegistry(Maps.immutableMap(this.roleRegistryMap));
        }

    }

}
