package domain.observer;

import domain.models.appointment.IAppointment;
import domain.models.patient.Patient;
import domain.facade.NotificationFacade;

public class PatientObserver implements IAppointmentObserver {
    private final Patient patient;
    private final NotificationFacade notificationFacade;

    public PatientObserver(Patient patient) {
        this.patient = patient;
        this.notificationFacade = new NotificationFacade();
    }

    @Override
    public void update(IAppointment appointment, String message) {
        if (appointment.getPatientId().equals(patient.getId())) { // Changed from getPatientId() to getId()
            String notificationMessage = String.format("Patient Notification: %s - Appointment %s",
                    message,
                    appointment.getDateTime());
            notificationFacade.sendAppointmentReminder(patient.getContactNumber(), notificationMessage);
            // Changed from getContactInfo() to getContactNumber() to match Patient class
        }
    }
}
