import domain.facade.HospitalFacade;
import domain.models.patient.InsuranceInfo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hospital Management System\n");

        // Create facade instance
        HospitalFacade hospitalFacade = new HospitalFacade();

        try {
            // 1. Adding Staff Members with more detailed output
            System.out.println("1. Adding Staff Members:");
            System.out.println("------------------------");
            String doctorId1 = hospitalFacade.addStaffMember("doctor", "Dr. Smith", "Cardiology");
            String doctorId2 = hospitalFacade.addStaffMember("doctor", "Dr. Johnson", "Pediatrics");
            String nurseId = hospitalFacade.addStaffMember("nurse", "Nurse Williams", "General");
            System.out.println(" Added Dr. Smith (Cardiology) - ID: " + doctorId1);
            System.out.println(" Added Dr. Johnson (Pediatrics) - ID: " + doctorId2);
            System.out.println(" Added Nurse Williams (General) - ID: " + nurseId);
            System.out.println();

            // 2. Adding Patients with more context
            System.out.println("2. Adding Patients:");
            System.out.println("------------------");
            InsuranceInfo insurance1 = new InsuranceInfo("POL123", "BlueCross", "2025-12-31");
            InsuranceInfo insurance2 = new InsuranceInfo("POL456", "HealthCare Plus", "2026-06-30");

            String patientId1 = hospitalFacade.addPatient("John Doe", 35, "1234567890", insurance1);
            String patientId2 = hospitalFacade.addPatient("Jane Smith", 28, "0987654321", insurance2);
            System.out.println(" Added John Doe (35 years) - ID: " + patientId1);
            System.out.println("  Insurance: BlueCross (Policy: POL123)");
            System.out.println(" Added Jane Smith (28 years) - ID: " + patientId2);
            System.out.println("  Insurance: HealthCare Plus (Policy: POL456)");
            System.out.println();

            // 3. Register Observers with detailed explanation
            System.out.println("3. Setting up Patient-Doctor Relationships (Observer Pattern):");
            System.out.println("--------------------------------------------------------");
            hospitalFacade.registerObservers(patientId1, doctorId1);
            hospitalFacade.registerObservers(patientId2, doctorId2);
            System.out.println(" Dr. Smith is now observing John Doe's appointments");
            System.out.println(" Dr. Johnson is now observing Jane Smith's appointments");
            System.out.println();

            // 4. Schedule Appointments with Command Pattern demonstration
            System.out.println("4. Creating New Appointments (Command Pattern):");
            System.out.println("-------------------------------------------");
            String appointmentId1 = hospitalFacade.scheduleAppointment(patientId1, doctorId1, "2024-11-25 10:00");
            System.out.println("→ New Appointment Created:");
            System.out.println("  Patient: John Doe");
            System.out.println("  Doctor: Dr. Smith");
            System.out.println("  Date/Time: 2024-11-25 10:00");
            System.out.println("  Appointment ID: " + appointmentId1);
            System.out.println("  [Observer Notification: Dr. Smith notified of new appointment]");
            System.out.println();

            String appointmentId2 = hospitalFacade.scheduleAppointment(patientId2, doctorId2, "2024-11-26 14:30");
            System.out.println("→ New Appointment Created:");
            System.out.println("  Patient: Jane Smith");
            System.out.println("  Doctor: Dr. Johnson");
            System.out.println("  Date/Time: 2024-11-26 14:30");
            System.out.println("  Appointment ID: " + appointmentId2);
            System.out.println("  [Observer Notification: Dr. Johnson notified of new appointment]");
            System.out.println();

            // 5. Demonstrate Notification System
            System.out.println("5. Testing Notification System:");
            System.out.println("-----------------------------");
            hospitalFacade.sendAppointmentNotification(appointmentId1, false);
            System.out.println("✓ Regular notification sent to John Doe and Dr. Smith");
            hospitalFacade.sendAppointmentNotification(appointmentId2, true);
            System.out.println("✓ Urgent notification sent to Jane Smith and Dr. Johnson");
            System.out.println();

            // 6. Demonstrate Appointment Rescheduling with Command Pattern
            System.out.println("6. Rescheduling Appointment (Command Pattern):");
            System.out.println("------------------------------------------");
            System.out.println("→ Original Appointment:");
            System.out.println("  Patient: John Doe");
            System.out.println("  Doctor: Dr. Smith");
            System.out.println("  Old Date/Time: 2024-11-25 10:00");

            hospitalFacade.rescheduleAppointment(appointmentId1, "2024-11-27 11:00");

            System.out.println("→ Appointment Rescheduled:");
            System.out.println("  New Date/Time: 2024-11-27 11:00");
            System.out.println("  [Observer Notification: Dr. Smith notified of appointment change]");
            System.out.println();

            // 7. Demonstrate Command Pattern's Undo Feature
            System.out.println("7. Testing Undo Operation (Command Pattern):");
            System.out.println("----------------------------------------");
            System.out.println("→ Undoing last rescheduling operation");
            hospitalFacade.undoLastAppointmentAction();
            System.out.println(" Appointment restored to original time: 2024-11-25 10:00");
            System.out.println("  [Observer Notification: Dr. Smith notified of appointment restoration]");
            System.out.println();

            // 8. Generate Detailed Reports
            System.out.println("8. Generating Comprehensive Reports:");
            System.out.println("--------------------------------");
            System.out.println("\nDetailed Report for John Doe:");
            System.out.println(hospitalFacade.generateReport(patientId1, appointmentId1));
            System.out.println("\nDetailed Report for Jane Smith:");
            System.out.println(hospitalFacade.generateReport(patientId2, appointmentId2));
            System.out.println();

            // 9. Display System Logs with timestamps
            System.out.println("9. System Activity Logs:");
            System.out.println("---------------------");
            System.out.println(hospitalFacade.getSystemLogs());

        } catch (Exception e) {
            System.err.println("Error occurred during testing:");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}