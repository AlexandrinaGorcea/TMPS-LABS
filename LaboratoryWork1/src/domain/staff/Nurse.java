package domain.staff;
import util.IdGenerator;

public class Nurse implements IStaffMember {
    private String id;
    private String name;
    private String department;

    public Nurse(String name, String department) {
        this.id = IdGenerator.getInstance().generateStaffId();
        this.name = name;
        this.department = department;
    }

    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public String getRole() { return "Nurse"; }

    public String getDepartment() { return department; }
}
