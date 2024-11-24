package service;

import domain.models.appointment.Appointment;
import domain.models.appointment.AppointmentStatus;
import domain.observer.AppointmentSubject;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments;
    private final AppointmentSubject appointmentSubject;

    public AppointmentService() {
        this.appointments = new HashMap<>();
        this.appointmentSubject = new AppointmentSubject();
    }

    public String scheduleAppointment(String patientId, String doctorId, String dateTime) {
        Appointment appointment = new Appointment(patientId, doctorId, dateTime);
        appointments.put(appointment.getAppointmentId(), appointment);
        return appointment.getAppointmentId();
    }

    public void rescheduleAppointment(String appointmentId, String newDateTime) {
        Appointment appointment = appointments.get(appointmentId);
        if (appointment != null) {
            Appointment newAppointment = new Appointment(
                    appointment.getPatientId(),
                    appointment.getDoctorId(),
                    newDateTime
            );
            appointments.put(appointmentId, newAppointment);
            appointmentSubject.notifyObservers(newAppointment, "Appointment rescheduled to " + newDateTime);
        }
    }

    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    public void cancelAppointment(String appointmentId) {
        Appointment appointment = appointments.get(appointmentId);
        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.CANCELLED);
            appointmentSubject.notifyObservers(appointment, "Appointment cancelled");
        }
    }

    public AppointmentSubject getAppointmentSubject() {
        return appointmentSubject;
    }

    public Map<String, Appointment> getAllAppointments() {
        return new HashMap<>(appointments);
    }
}