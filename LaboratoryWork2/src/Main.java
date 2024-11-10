public class Main {
    public static void main(String[] args) {
        HospitalManagementClient client = new HospitalManagementClient();

        // Example usage
        InsuranceInfo insurance = new InsuranceInfo("POL123", "HealthCare Inc", "2025-12-31");
        client.registerNewPatient("John Doe", 35, "1234567890", insurance);
        client.scheduleEmergencyAppointment("P0001", "D0001", "CRITICAL");
    }
}