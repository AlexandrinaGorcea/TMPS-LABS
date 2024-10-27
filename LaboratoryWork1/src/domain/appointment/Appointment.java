package domain.appointment;
import util.*;
public class Appointment implements IAppointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String dateTime;

    public Appointment(String patientId, String doctorId, String dateTime) {
        this.appointmentId = IdGenerator.getInstance().generateAppointmentId();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
    }

    @Override
    public String getAppointmentId() { return appointmentId; }
    @Override
    public String getPatientId() { return patientId; }
    @Override
    public String getDoctorId() { return doctorId; }
    @Override
    public String getDateTime() { return dateTime; }
}