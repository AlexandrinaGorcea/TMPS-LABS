package domain.command;

import domain.models.appointment.IAppointment;
import util.singleton.HospitalSystem;
import domain.observer.AppointmentSubject;

public class CreateAppointmentCommand implements IAppointmentCommand {
    private final IAppointment appointment;
    private final HospitalSystem hospitalSystem;
    private final AppointmentSubject appointmentSubject;

    public CreateAppointmentCommand(IAppointment appointment) {
        this.appointment = appointment;
        this.hospitalSystem = HospitalSystem.getInstance();
        this.appointmentSubject = new AppointmentSubject();
    }

    @Override
    public void execute() {
        hospitalSystem.scheduleAppointment(appointment);
        // Add a descriptive message when notifying observers
        appointmentSubject.notifyObservers(appointment,
                "New appointment scheduled for " + appointment.getDateTime());
    }

    @Override
    public void undo() {
        hospitalSystem.cancelAppointment(appointment.getAppointmentId());
    }
}