package domain.staff;

import util.IdGenerator;

public class Doctor implements IStaffMember {
    private String id;
    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.id = IdGenerator.getInstance().generateStaffId();
        this.name = name;
        this.specialization = specialization;
    }

    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public String getRole() { return "Doctor"; }

    public String getSpecialization() { return specialization; }
}
