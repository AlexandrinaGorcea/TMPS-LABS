package util;

public class IdGenerator {
    private static IdGenerator instance;
    private int patientCounter = 0;
    private int staffCounter = 0;
    private int appointmentCounter = 0;

    private IdGenerator() {}

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public String generatePatientId() {
        return "P" + String.format("%04d", ++patientCounter);
    }

    public String generateStaffId() {
        return "S" + String.format("%04d", ++staffCounter);
    }

    public String generateAppointmentId() {
        return "A" + String.format("%04d", ++appointmentCounter);
    }
}