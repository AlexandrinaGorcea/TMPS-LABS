package domain.observer;

import domain.models.appointment.IAppointment;
import domain.models.staff.Doctor;
import domain.facade.NotificationFacade;

public class DoctorObserver implements IAppointmentObserver {
    private final Doctor doctor;
    private final NotificationFacade notificationFacade;

    public DoctorObserver(Doctor doctor) {
        this.doctor = doctor;
        this.notificationFacade = new NotificationFacade();
    }

    @Override
    public void update(IAppointment appointment, String message) {
        if (appointment.getDoctorId().equals(doctor.getId())) {
            String notificationMessage = String.format("Doctor Notification: %s - Appointment %s",
                    message,
                    appointment.getDateTime());

            if (doctor.getContactNumber() != null) {
                notificationFacade.sendAppointmentReminder(doctor.getContactNumber(), notificationMessage);
            }
        }
    }
}