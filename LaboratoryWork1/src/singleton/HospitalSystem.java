package singleton;
import domain.appointment.*;
import domain.staff.*;
import java.util.ArrayList;
import java.util.List;
import domain.Patient;


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
}