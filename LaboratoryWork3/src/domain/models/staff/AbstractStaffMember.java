package domain.models.staff;

import java.util.UUID;

public abstract class AbstractStaffMember implements IStaffMember {
    protected String id;
    protected String name;
    protected String specialization;
    protected String contactNumber;  // Added this field

    protected AbstractStaffMember(String name, String specialization) {
        this.id = generateId();
        this.name = name;
        this.specialization = specialization;
    }

    private String generateId() {
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

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}