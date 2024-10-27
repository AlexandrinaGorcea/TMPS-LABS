package builder;
import domain.Patient;
import domain.staff.*;
import domain.appointment.*;

public class ReportBuilder {
    private StringBuilder report;

    public ReportBuilder() {
        this.report = new StringBuilder();
    }

    public ReportBuilder addHeader(String header) {
        report.append("=== ").append(header).append(" ===\n");
        return this;
    }

    public ReportBuilder addPatientInfo(Patient patient) {
        report.append("Patient ID: ").append(patient.getId()).append("\n")
                .append("Name: ").append(patient.getName()).append("\n")
                .append("Contact: ").append(patient.getContactNumber()).append("\n");
        return this;
    }

    public ReportBuilder addAppointmentInfo(Appointment appointment) {
        report.append("Appointment ID: ").append(appointment.getAppointmentId()).append("\n")
                .append("DateTime: ").append(appointment.getDateTime()).append("\n");
        return this;
    }

    public ReportBuilder addSection(String content) {
        report.append(content).append("\n");
        return this;
    }

    public String build() {
        return report.toString();
    }
}