package util.singleton;
import domain.models.appointment.*;
import domain.models.staff.*;
import java.util.ArrayList;
import java.util.List;
import domain.models.patient.*;
import java.util.stream.Collectors;


public class HospitalSystem {
    private static HospitalSystem instance;
    private final List<Patient> patients;
    private final List<IStaffMember> staff;
    private final List<Appointment> appointments;
    private final Logger logger;

    private HospitalSystem() {
        patients = new ArrayList<>();
        staff = new ArrayList<>();
        appointments = new ArrayList<>();
        logger = Logger.getInstance();
    }

    public static synchronized HospitalSystem getInstance() {
        if (instance == null) {
            instance = new HospitalSystem();
        }
        return instance;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        logger.log("New patient added: " + patient.getId());
    }

    public void addStaffMember(IStaffMember staffMember) {
        staff.add(staffMember);
        logger.log("New staff member added: " + staffMember.getId());
    }

    public void scheduleAppointment(Appointment appointment) {
        appointments.add(appointment);
        logger.log("New appointment scheduled: " + appointment.getAppointmentId());
    }
    public Appointment getAppointment(String appointmentId) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }

    public Patient getPatient(String patientId) {
        return patients.stream()
                .filter(p -> p.getId().equals(patientId))
                .findFirst()
                .orElse(null);
    }

    public IStaffMember getDoctor(String doctorId) {
        return staff.stream()
                .filter(s -> s.getId().equals(doctorId) && s.getRole().equals("Doctor"))
                .findFirst()
                .orElse(null);
    }

    public List<Appointment> getPatientAppointments(String patientId) {
        return appointments.stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    public void updateAppointment(String appointmentId, IAppointment appointment) {
        int index = appointments.indexOf(getAppointment(appointmentId));
        if (index != -1) {
            appointments.set(index, (Appointment) appointment);
        }
    }


}