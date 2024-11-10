package domain.adapter;

import domain.models.patient.Patient;
import domain.models.appointment.Appointment;
import java.util.List;

public interface IHealthSystemAdapter {
    String importPatientData(String externalId);
    void exportPatientData(Patient patient);
    List<Appointment> getExternalAppointments(String patientId);
    void syncAppointment(Appointment appointment);
}