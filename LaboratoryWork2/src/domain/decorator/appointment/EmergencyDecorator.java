package domain.decorator.appointment;

import domain.models.appointment.AppointmentStatus;
import domain.models.appointment.IAppointment;

public class EmergencyDecorator implements IAppointment {
    private final IAppointment appointment;

    public EmergencyDecorator(IAppointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String getAppointmentId() {
        return appointment.getAppointmentId();
    }

    @Override
    public String getPatientId() {
        return appointment.getPatientId();
    }

    @Override
    public String getDoctorId() {
        return appointment.getDoctorId();
    }

    @Override
    public String getDateTime() {
        return appointment.getDateTime();
    }

    @Override
    public AppointmentStatus getStatus() {
        return appointment.getStatus();
    }

    @Override
    public void setStatus(AppointmentStatus status) {
        appointment.setStatus(status);
    }

    public String getPriority() {
        return "EMERGENCY";
    }

    public boolean requiresImmediate() {
        return true;
    }
}