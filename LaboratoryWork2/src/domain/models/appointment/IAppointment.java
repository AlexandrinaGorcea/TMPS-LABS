package domain.models.appointment;

public interface IAppointment {
    String getAppointmentId();
    String getPatientId();
    String getDoctorId();
    String getDateTime();
    AppointmentStatus getStatus();
    void setStatus(AppointmentStatus status);
}