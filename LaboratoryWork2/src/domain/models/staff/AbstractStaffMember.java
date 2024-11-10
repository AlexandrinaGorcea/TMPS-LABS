package domain.models.staff;

import java.util.UUID;

public abstract class AbstractStaffMember implements IStaffMember {
    protected String id;
    protected String name;
    protected String specialization;

    protected AbstractStaffMember(String name, String specialization) {
        this.id = generateId();
        this.name = name;
        this.specialization = specialization;
    }

    private String generateId() {
        // Generate a unique ID with role prefix
        return getRole().toUpperCase().substring(0, 3) + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSpecialization() {
        return specialization;
    }
}