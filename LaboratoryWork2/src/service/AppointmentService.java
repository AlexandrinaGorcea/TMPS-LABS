package service;

import domain.models.appointment.Appointment;
import domain.models.appointment.AppointmentStatus;
import util.singleton.HospitalSystem;
import util.singleton.Logger;

public class AppointmentService {
    private final HospitalSystem hospitalSystem;
    private final Logger logger;

    public AppointmentService() {
        this.hospitalSystem = HospitalSystem.getInstance();
        this.logger = Logger.getInstance();
    }

    public boolean validateAppointment(String patientId, String doctorId, String dateTime) {
        // Add validation logic
        return hospitalSystem.getPatient(patientId) != null &&
                hospitalSystem.getDoctor(doctorId) != null &&
                dateTime != null && !dateTime.isEmpty();
    }

    public Appointment createAppointment(String patientId, String doctorId, String dateTime) {
        if (!validateAppointment(patientId, doctorId, dateTime)) {
            throw new IllegalArgumentException("Invalid appointment data");
        }
        return new Appointment(patientId, doctorId, dateTime);
    }

    public void updateAppointmentStatus(String appointmentId, AppointmentStatus status) {
        logger.log("Updating appointment " + appointmentId + " status to " + status);
        // Add status update logic
    }
}
