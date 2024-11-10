package domain.factory;
import domain.models.patient.*;
import util.singleton.Logger;
import util.ValidationUtils;

public class PatientFactory {
    public static Patient createPatient(String name, int age, String contactNumber, InsuranceInfo insuranceInfo) {
        ValidationUtils.validatePatientData(name, age, contactNumber);

        Patient patient = new Patient(name, age, contactNumber);
        patient.setInsuranceInfo(insuranceInfo);

        Logger.getInstance().log("Created new patient: " + patient.getId());

        return patient;
    }
}