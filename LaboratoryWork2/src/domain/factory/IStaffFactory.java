package domain.factory;

import domain.models.staff.IStaffMember;

public interface IStaffFactory {
    IStaffMember createStaffMember(String type, String name, String specialization);
}