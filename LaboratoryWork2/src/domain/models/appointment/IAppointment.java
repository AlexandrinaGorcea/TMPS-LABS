package domain.models.appointment;
import java.time.LocalDateTime;


public interface IAppointment {
    String getAppointmentId();
    String getPatientId();
    String getDoctorId();
    LocalDateTime getDateTime();
    AppointmentStatus getStatus();
    void setStatus(AppointmentStatus status);
}