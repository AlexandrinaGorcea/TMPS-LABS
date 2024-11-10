package domain.models.staff;

public abstract class AbstractStaffMember implements IStaffMember {
    protected String id;
    protected String name;

    protected AbstractStaffMember(String name, String specialization) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}