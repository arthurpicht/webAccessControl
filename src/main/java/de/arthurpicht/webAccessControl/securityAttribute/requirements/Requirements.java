package de.arthurpicht.webAccessControl.securityAttribute.requirements;

import de.arthurpicht.utils.core.strings.Strings;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Requirements implements Serializable {

    private static final long serialVersionUID = -6144533587294307683L;
    private final LinkedHashSet<Requirement> requirementsSet;

    public Requirements() {
        this.requirementsSet = new LinkedHashSet<>();
    }

    public void addRequirement(Requirement requirement) {
        this.requirementsSet.add(requirement);
    }

    public void add2FARequirement() {
        this.requirementsSet.add(new SecondFARequirement());
    }

    public void addPolicyConsentRequirement() {
        this.requirementsSet.add(new PolicyConsentRequirement());
    }

    public void addEmailConfirmationRequirement(String token, String code) {
        this.requirementsSet.add(new EmailConfirmationRequirement(token, code));
    }

    public void addRenewPasswordRequirement() {
        this.requirementsSet.add(new RenewPWRequirement());
    }

    public boolean hasRequirements() {
        return !this.requirementsSet.isEmpty();
    }

    public Requirement getNextRequirement() {
        Optional<Requirement> requirementOptional = this.requirementsSet.stream().findFirst();
        if (requirementOptional.isEmpty()) throw new IllegalStateException("No next requirement.");
        return requirementOptional.get();
    }

    public String getNextRequirementAsString() {
        Requirement requirement = getNextRequirement();
        return requirement.getName();
    }

    public void markNextRequirementAsFulfilled() {
        Requirement requirement = getNextRequirement();
        this.requirementsSet.remove(requirement);
    }

    @Override
    public String toString() {
        List<String> names = this.requirementsSet.stream().map(Requirement::getName).collect(Collectors.toList());
        return names.isEmpty() ?
                "Requirements: none." :
                "Requirements: " + Strings.listing(names, ", ") + ".";
    }

}
