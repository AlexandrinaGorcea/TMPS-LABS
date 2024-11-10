package domain.models.appointment;

public class Appointment implements IAppointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String dateTime;
    private AppointmentStatus status;

    public Appointment(String patientId, String doctorId, String dateTime) {
        this.appointmentId = "APT" + System.currentTimeMillis();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = AppointmentStatus.SCHEDULED;
    }

    @Override
    public String getAppointmentId() {
        return appointmentId;
    }

    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public String getDoctorId() {
        return doctorId;
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public AppointmentStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}

