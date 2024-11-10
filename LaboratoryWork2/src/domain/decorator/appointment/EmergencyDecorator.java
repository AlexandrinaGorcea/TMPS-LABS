package domain.decorator.appointment;

import domain.models.appointment.IAppointment;

public class EmergencyDecorator extends AppointmentDecorator {
    public EmergencyDecorator(IAppointment appointment) {
        super(appointment);
    }

    public String getPriority() {
        return "EMERGENCY";
    }

    public boolean requiresImmediate() {
        return true;
    }
}