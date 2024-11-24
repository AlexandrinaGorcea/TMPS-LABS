package domain.command;

import domain.models.appointment.IAppointment;
import util.singleton.HospitalSystem;
import domain.observer.AppointmentSubject;

public class RescheduleAppointmentCommand implements IAppointmentCommand {
    private final String appointmentId;
    private final String newDateTime;
    private final HospitalSystem hospitalSystem;
    private final AppointmentSubject appointmentSubject;
    private String oldDateTime;

    public RescheduleAppointmentCommand(String appointmentId, String newDateTime) {
        this.appointmentId = appointmentId;
        this.newDateTime = newDateTime;
        this.hospitalSystem = HospitalSystem.getInstance();
        this.appointmentSubject = new AppointmentSubject();
    }

    @Override
    public void execute() {
        IAppointment appointment = hospitalSystem.getAppointment(appointmentId);
        if (appointment != null) {
            oldDateTime = appointment.getDateTime();
            appointment.setDateTime(newDateTime);
            hospitalSystem.updateAppointment(appointmentId, appointment);
            appointmentSubject.notifyObservers(appointment, "Appointment rescheduled to " + newDateTime);
        }
    }

    @Override
    public void undo() {
        IAppointment appointment = hospitalSystem.getAppointment(appointmentId);
        if (appointment != null) {
            appointment.setDateTime(oldDateTime);
            hospitalSystem.updateAppointment(appointmentId, appointment);
            appointmentSubject.notifyObservers(appointment, "Appointment reverted to " + oldDateTime);
        }
    }
}
