import domain.facade.HospitalFacade;
import domain.facade.NotificationFacade;
import domain.facade.MedicalRecordFacade;
import domain.models.patient.InsuranceInfo;

import util.singleton.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize facades
            HospitalFacade hospitalFacade = new HospitalFacade();
            NotificationFacade notificationFacade = new NotificationFacade();
            MedicalRecordFacade medicalRecordFacade = new MedicalRecordFacade();

            System.out.println("=== Hospital Management System Test ===\n");

            // 1. Add Staff Members
            System.out.println("1. Adding Staff Members...");
            String doctorId = hospitalFacade.addStaffMember("doctor", " Smith", "Cardiology");
            String nurseId = hospitalFacade.addStaffMember("nurse", "Nurse Johnson", "Emergency");
            System.out.println("Added Doctor ID: " + doctorId);
            System.out.println("Added Nurse ID: " + nurseId);

            // 2. Register Patients
            System.out.println("\n2. Registering Patients...");
            InsuranceInfo insurance1 = new InsuranceInfo("POL123", "HealthCare Inc", "2025-12-31");
            InsuranceInfo insurance2 = new InsuranceInfo("POL456", "MediCare Ltd", "2026-06-30");

            String patient1Id = hospitalFacade.addPatient("John Doe", 35, "1234567890", insurance1);
            String patient2Id = hospitalFacade.addPatient("Jane Smith", 28, "9876543210", insurance2);
            System.out.println("Added Patient 1 ID: " + patient1Id);
            System.out.println("Added Patient 2 ID: " + patient2Id);

            // 3. Schedule Appointments
            System.out.println("\n3. Scheduling Appointments...");
            String appointment1Id = hospitalFacade.scheduleAppointment(patient1Id, doctorId, "2024-10-28 10:00");
            String appointment2Id = hospitalFacade.scheduleAppointment(patient2Id, doctorId, "2024-10-28 14:00");
            System.out.println("Scheduled Appointment 1 ID: " + appointment1Id);
            System.out.println("Scheduled Appointment 2 ID: " + appointment2Id);

            // 4. Test Emergency Decorator
            System.out.println("\n4. Testing Emergency Appointment...");
            hospitalFacade.markAppointmentAsEmergency(appointment1Id);
            System.out.println("Marked Appointment 1 as Emergency");

            // 5. Test Notification System
            System.out.println("\n5. Testing Notification System...");
            // Regular notification
            hospitalFacade.sendAppointmentNotification(appointment2Id, false);
            // Urgent notification
            hospitalFacade.sendAppointmentNotification(appointment1Id, true);

            // 6. Generate Reports
            System.out.println("\n6. Generating Reports...");
            // Patient report
            String patientReport = medicalRecordFacade.generatePatientReport(patient1Id);
            System.out.println(patientReport);

            // Appointment history
            String appointmentHistory = medicalRecordFacade.generateAppointmentHistory(patient1Id);
            System.out.println(appointmentHistory);

            // 7. Test External System Integration
            System.out.println("\n7. Testing External System Integration...");
            hospitalFacade.syncWithExternalSystems(patient1Id);

            // 8. Print System Logs
            System.out.println("\n8. System Logs:");
            System.out.println(Logger.getInstance().getLogs());

        } catch (Exception e) {
            System.err.println("Error occurred during testing:");
            e.printStackTrace();
        }
    }

    private static void printSection(String section) {
        System.out.println("\n=== " + section + " ===");
    }
}