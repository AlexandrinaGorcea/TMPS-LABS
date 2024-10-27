package factory;

import domain.staff.IStaffMember;

public interface IStaffFactory {
    IStaffMember createStaffMember(String type, String name, String specialization);
}