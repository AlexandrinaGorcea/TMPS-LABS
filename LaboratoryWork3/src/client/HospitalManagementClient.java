package client;

import domain.facade.HospitalFacade;
import domain.models.patient.InsuranceInfo;
import domain.models.appointment.*;

public class HospitalManagementClient {
    public static void main(String[] args) {
        HospitalFacade hospitalFacade = new HospitalFacade();

        // Create staff members
        String doctorId = hospitalFacade.addStaffMember("doctor", "Dr. Smith", "Cardiology");
        hospitalFacade.addStaffMember("nurse", "Nurse Johnson", "Emergency");

        // Create patient
        InsuranceInfo insurance = new InsuranceInfo("POL123", "HealthCare Inc", "2025-12-31");
        String patientId = hospitalFacade.addPatient("John Doe", 35, "1234567890", insurance);

        // Register observers for notifications
        hospitalFacade.registerObservers(patientId, doctorId);

        // Schedule appointment using Command pattern
        String appointmentId = hospitalFacade.scheduleAppointment(patientId, doctorId, "2024-10-28 10:00");

        // Send notification
        hospitalFacade.sendAppointmentNotification(appointmentId, true);

        // Try rescheduling
        hospitalFacade.rescheduleAppointment(appointmentId, "2024-10-29 11:00");

        // Undo the reschedule if needed
        // hospitalFacade.undoLastAppointmentAction();

        // Generate and print report
        System.out.println(hospitalFacade.generateReport(patientId, appointmentId));

        System.out.println("\nSystem Logs:");
        System.out.println(hospitalFacade.getSystemLogs());
    }
}