package domain.observer;

import domain.models.appointment.IAppointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSubject {
    private final List<IAppointmentObserver> observers = new ArrayList<>();

    public void attach(IAppointmentObserver observer) {
        observers.add(observer);
    }

    public void detach(IAppointmentObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(IAppointment appointment, String message) {
        for (IAppointmentObserver observer : observers) {
            observer.update(appointment, message);
        }
    }
}