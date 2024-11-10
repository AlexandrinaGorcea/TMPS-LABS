package domain.adapter;

import domain.models.patient.Patient;
import domain.models.appointment.IAppointment;
import util.singleton.Logger;
import java.util.List;
import java.util.ArrayList;

public class HealthAPIAdapter implements IHealthSystemAdapter {
    private final Logger logger = Logger.getInstance();

    @Override
    public String importPatientData(String externalId) {
        logger.log("Importing patient data from Health API: " + externalId);
        return "API_" + externalId;
    }

    @Override
    public void exportPatientData(Patient patient) {
        logger.log("Exporting patient data to Health API: " + patient.getId());
    }

    @Override
    public List<IAppointment> getExternalAppointments(String patientId) {
        logger.log("Fetching appointments from Health API for patient: " + patientId);
        return new ArrayList<>();
    }

    @Override
    public void syncAppointment(IAppointment appointment) {
        logger.log("Syncing appointment to Health API: " + appointment.getAppointmentId());
    }
}