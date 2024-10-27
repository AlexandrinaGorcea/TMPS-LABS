import builder.ReportBuilder;
import domain.appointment.*;
import domain.staff.*;
import domain.*;
import factory.*;
import singleton.*;

cd
public class Main {
    public static void main(String[] args) {
        // Get hospital system instance
        HospitalSystem hospitalSystem = HospitalSystem.getInstance();

        // Create staff using factory
        IStaffFactory staffFactory = new StaffFactory();
        IStaffMember doctor = staffFactory.createStaffMember("doctor", "Dr. Smith", "Cardiology");
        IStaffMember nurse = staffFactory.createStaffMember("nurse", "Nurse Johnson", "Emergency");

        // Add staff to system
        hospitalSystem.addStaffMember(doctor);
        hospitalSystem.addStaffMember(nurse);

        // Create patient using factory
        InsuranceInfo insurance = new InsuranceInfo("POL123", "HealthCare Inc", "2025-12-31");
        Patient patient = PatientFactory.createPatient("John Doe", 35, "1234567890", insurance);
        hospitalSystem.addPatient(patient);

        // Schedule appointment
        Appointment appointment = new Appointment(patient.getId(), doctor.getId(), "2024-10-28 10:00");
        hospitalSystem.scheduleAppointment(appointment);

        // Generate report using builder
        ReportBuilder reportBuilder = new ReportBuilder();
        String report = reportBuilder
                .addHeader("Patient Visit Report")
                .addPatientInfo(patient)
                .addAppointmentInfo(appointment)
                .addSection("Notes: Regular checkup scheduled")
                .build();

        System.out.println(report);
        System.out.println("\nSystem Logs:");
        System.out.println(Logger.getInstance().getLogs());
    }
}