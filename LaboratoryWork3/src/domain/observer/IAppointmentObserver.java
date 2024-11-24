package domain.observer;

import domain.models.appointment.IAppointment;

public interface IAppointmentObserver {
    void update(IAppointment appointment, String message);
}