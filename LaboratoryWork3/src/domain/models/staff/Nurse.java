package domain.models.staff;

public class Nurse extends AbstractStaffMember {
    public Nurse(String name, String specialization) {
        super(name, specialization);
    }

    @Override
    public String getRole() {
        return "Nurse";
    }
}