package domain.facade;

import domain.builder.ReportBuilder;
import domain.models.patient.Patient;
import domain.models.appointment.Appointment;
import util.singleton.HospitalSystem;

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

        // Add appointment history
        for (Appointment appointment : hospitalSystem.getPatientAppointments(patientId)) {
            builder.addAppointmentInfo(appointment);
        }

        return builder.build();
    }
}