package domain.adapter;

import domain.models.appointment.IAppointment;
import domain.models.patient.Patient;
import java.util.List;

public interface IHealthSystemAdapter {
    String importPatientData(String externalId);
    void exportPatientData(Patient patient);
    List<IAppointment> getExternalAppointments(String patientId);
    void syncAppointment(IAppointment appointment);
}