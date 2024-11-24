package domain.builder;

import domain.models.patient.Patient;
import domain.models.appointment.IAppointment;

public class ReportBuilder {
    private StringBuilder report = new StringBuilder();

    public ReportBuilder addHeader(String header) {
        report.append(header).append("\n\n");
        return this;
    }

    public ReportBuilder addPatientInfo(Patient patient) {
        report.append("Patient Information:\n");
        report.append("Name: ").append(patient.getName()).append("\n");
        report.append("ID: ").append(patient.getId()).append("\n");
        return this;
    }

    public ReportBuilder addAppointmentInfo(IAppointment appointment) {
        report.append("Appointment Information:\n");
        report.append("ID: ").append(appointment.getAppointmentId()).append("\n");
        report.append("Date/Time: ").append(appointment.getDateTime()).append("\n");
        return this;
    }

    public ReportBuilder addSection(String section) {
        report.append("\n").append(section).append("\n");
        return this;
    }

    public String build() {
        return report.toString();
    }
}