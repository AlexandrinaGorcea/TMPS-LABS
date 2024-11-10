package domain.decorator.appointment;

import domain.models.appointment.IAppointment;

public abstract class AppointmentDecorator implements IAppointment {
    protected IAppointment appointment;

    public AppointmentDecorator(IAppointment appointment) {
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
}
