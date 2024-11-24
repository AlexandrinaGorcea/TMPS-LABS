package domain.facade;

import domain.factory.StaffFactory;
import domain.factory.PatientFactory;
import domain.models.patient.InsuranceInfo;
import domain.models.patient.Patient;
import domain.models.appointment.Appointment;
import domain.models.appointment.IAppointment;
import domain.models.staff.IStaffMember;
import domain.models.staff.Doctor;
import domain.bridge.notification.EmailSender;
import domain.bridge.notification.SMSSender;
import domain.bridge.message.AppointmentMessage;
import util.singleton.HospitalSystem;
import util.singleton.Logger;
import domain.builder.ReportBuilder;
import domain.command.*;
import domain.observer.*;
import java.util.List;

public class HospitalFacade {
    private final HospitalSystem hospitalSystem;
    private final StaffFactory staffFactory;
    private final Logger logger;
    private final EmailSender emailSender;
    private final SMSSender smsSender;
    private final AppointmentInvoker appointmentInvoker;
    private final AppointmentSubject appointmentSubject;

    public HospitalFacade() {
        this.hospitalSystem = HospitalSystem.getInstance();
        this.staffFactory = new StaffFactory();
        this.logger = Logger.getInstance();
        this.emailSender = new EmailSender();
        this.smsSender = new SMSSender();
        this.appointmentInvoker = new AppointmentInvoker();
        this.appointmentSubject = new AppointmentSubject();
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
        CreateAppointmentCommand command = new CreateAppointmentCommand(appointment);
        appointmentInvoker.executeCommand(command);
        return appointment.getAppointmentId();
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

    public void registerObservers(String patientId, String doctorId) {
        Patient patient = hospitalSystem.getPatient(patientId);
        Doctor doctor = (Doctor) hospitalSystem.getDoctor(doctorId);

        appointmentSubject.attach(new PatientObserver(patient));
        appointmentSubject.attach(new DoctorObserver(doctor));
    }

    public void rescheduleAppointment(String appointmentId, String newDateTime) {
        RescheduleAppointmentCommand command = new RescheduleAppointmentCommand(appointmentId, newDateTime);
        appointmentInvoker.executeCommand(command);
    }

    public void undoLastAppointmentAction() {
        appointmentInvoker.undoLastCommand();
    }

    public String getSystemLogs() {
        return logger.getLogs();
    }
}