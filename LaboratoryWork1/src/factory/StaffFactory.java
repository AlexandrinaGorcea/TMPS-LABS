package factory;

import domain.staff.Doctor;
import domain.staff.IStaffMember;
import domain.staff.Nurse;

public class StaffFactory implements IStaffFactory {
    @Override
    public IStaffMember createStaffMember(String type, String name, String specialization) {
        switch (type.toLowerCase()) {
            case "doctor":
                return new Doctor(name, specialization);
            case "nurse":
                return new Nurse(name, specialization);
            default:
                throw new IllegalArgumentException("Invalid staff type: " + type);
        }
    }
}