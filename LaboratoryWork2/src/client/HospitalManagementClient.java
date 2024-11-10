package client;

import domain.facade.HospitalFacade;
import domain.models.patient.InsuranceInfo;
import domain.models.appointment.*;

public class HospitalManagementClient {
    public static void main(String[] args) {
        HospitalFacade hospitalFacade = new HospitalFacade();


        String doctorId = hospitalFacade.addStaffMember("doctor", "Dr. Smith", "Cardiology");
        hospitalFacade.addStaffMember("nurse", "Nurse Johnson", "Emergency");


        InsuranceInfo insurance = new InsuranceInfo("POL123", "HealthCare Inc", "2025-12-31");
        String patientId = hospitalFacade.addPatient("John Doe", 35, "1234567890", insurance);


        String appointmentId = hospitalFacade.scheduleAppointment(patientId, doctorId, "2024-10-28 10:00");


        hospitalFacade.markAppointmentAsEmergency(appointmentId);


        hospitalFacade.sendAppointmentNotification(appointmentId, true);


        System.out.println(hospitalFacade.generateReport(patientId, appointmentId));
        System.out.println("\nSystem Logs:");
        System.out.println(hospitalFacade.getSystemLogs());
    }
}