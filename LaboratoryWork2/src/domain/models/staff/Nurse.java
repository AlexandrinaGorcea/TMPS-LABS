package domain.models.staff;

public class Nurse extends AbstractStaffMember {
    private String department;

    public Nurse(String name, String department) {
        super(name, department);
        this.department = department;
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public String getDepartment() {
        return department;
    }
}