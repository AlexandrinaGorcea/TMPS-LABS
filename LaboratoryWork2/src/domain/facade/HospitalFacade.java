package domain.facade;

import domain.factory.StaffFactory;
import domain.factory.PatientFactory;
import domain.models.patient.InsuranceInfo;
import domain.models.patient.Patient;
import domain.models.appointment.Appointment;
import domain.models.appointment.IAppointment;
import domain.models.staff.IStaffMember;
import domain.decorator.appointment.EmergencyDecorator;
import domain.bridge.notification.EmailSender;
import domain.bridge.notification.SMSSender;
import domain.bridge.message.AppointmentMessage;
import util.singleton.HospitalSystem;
import util.singleton.Logger;
import domain.builder.ReportBuilder;
import domain.adapter.IHealthSystemAdapter;
import domain.adapter.LegacySystemAdapter;
import domain.adapter.HealthAPIAdapter;
import java.util.List;

public class HospitalFacade {
    private final HospitalSystem hospitalSystem;
    private final StaffFactory staffFactory;
    private final IHealthSystemAdapter legacyAdapter;
    private final IHealthSystemAdapter apiAdapter;
    private final Logger logger;
    private final EmailSender emailSender;
    private final SMSSender smsSender;

    public HospitalFacade() {
        this.hospitalSystem = HospitalSystem.getInstance();
        this.staffFactory = new StaffFactory();
        this.logger = Logger.getInstance();
        this.emailSender = new EmailSender();
        this.smsSender = new SMSSender();
        this.legacyAdapter = new LegacySystemAdapter();
        this.apiAdapter = new HealthAPIAdapter();
    }

    public String addStaffMember(String type, String name, String specialization) {
        IStaffMember staff = staffFactory.createStaffMember(type, name, specialization);
        hospitalSystem.addStaffMember(staff);
        return staff.getId();
    }

    public String addPatient(String name, int age, String contactNumber, InsuranceInfo insurance) {
        Patient patient = PatientFactory.createPatient(name, age, contactNumber, insurance);
        hospitalSystem.addPatient(patient);
        return patient.getId();
    }

    public String scheduleAppointment(String patientId, String doctorId, String dateTime) {
        IAppointment appointment = new Appointment(patientId, doctorId, dateTime);
        hospitalSystem.scheduleAppointment(appointment);
        return appointment.getAppointmentId();
    }

    public void markAppointmentAsEmergency(String appointmentId) {
        IAppointment appointment = hospitalSystem.getAppointment(appointmentId);
        EmergencyDecorator emergencyAppointment = new EmergencyDecorator(appointment);
        hospitalSystem.updateAppointment(appointmentId, emergencyAppointment);
    }

    public void sendAppointmentNotification(String appointmentId, boolean urgent) {
        IAppointment appointment = hospitalSystem.getAppointment(appointmentId);
        Patient patient = hospitalSystem.getPatient(appointment.getPatientId());

        String details = String.format("Appointment scheduled for %s with Dr. %s",
                appointment.getDateTime(),
                hospitalSystem.getDoctor(appointment.getDoctorId()).getName());

        if (urgent) {
            new AppointmentMessage(smsSender, "URGENT: " + details)
                    .send(patient.getContactNumber());
        } else {
            new AppointmentMessage(emailSender, details)
                    .send(patient.getContactNumber());
        }
    }


    public String generateReport(String patientId, String appointmentId) {
        Patient patient = hospitalSystem.getPatient(patientId);
        IAppointment appointment = hospitalSystem.getAppointment(appointmentId);

        return new ReportBuilder()
                .addHeader("Patient Visit Report")
                .addPatientInfo(patient)
                .addAppointmentInfo(appointment)
                .addSection("Status: " + "Regular")
                .build();
    }

    public void syncWithExternalSystems(String patientId) {
        Patient patient = hospitalSystem.getPatient(patientId);
        if (patient != null) {
            legacyAdapter.exportPatientData(patient);
            apiAdapter.exportPatientData(patient);

            List<IAppointment> appointments = hospitalSystem.getPatientAppointments(patientId);
            for (IAppointment apt : appointments) {
                legacyAdapter.syncAppointment(apt);
                apiAdapter.syncAppointment(apt);
            }
        }
    }

    public String getSystemLogs() {
        return logger.getLogs();
    }
}