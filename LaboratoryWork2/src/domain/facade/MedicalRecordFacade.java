package domain.facade;

import domain.builder.ReportBuilder;
import domain.models.patient.Patient;
import domain.models.appointment.IAppointment;
import util.singleton.HospitalSystem;
import java.util.List;

public class MedicalRecordFacade {
    private final HospitalSystem hospitalSystem;

    public MedicalRecordFacade() {
        this.hospitalSystem = HospitalSystem.getInstance();
    }

    public String generatePatientReport(String patientId) {
        Patient patient = hospitalSystem.getPatient(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }

        ReportBuilder builder = new ReportBuilder()
                .addHeader("Patient Medical Record")
                .addPatientInfo(patient)
                .addSection("Insurance Information")
                .addSection("Provider: " + patient.getInsuranceInfo().getProvider())
                .addSection("Policy Number: " + patient.getInsuranceInfo().getPolicyNumber());

        return builder.build();
    }

    public String generateAppointmentHistory(String patientId) {
        Patient patient = hospitalSystem.getPatient(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }

        ReportBuilder builder = new ReportBuilder()
                .addHeader("Appointment History")
                .addPatientInfo(patient);

        List<IAppointment> appointments = hospitalSystem.getPatientAppointments(patientId);
        for (IAppointment appointment : appointments) {
            builder.addAppointmentInfo(appointment);
        }

        return builder.build();
    }
}