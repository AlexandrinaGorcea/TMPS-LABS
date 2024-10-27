package factory;
import domain.*;
import util.*;
public class PatientFactory {
    public static Patient createPatient(String name, int age, String contactNumber, InsuranceInfo insuranceInfo) {
        ValidationUtils.validatePatientData(name, age, contactNumber);
        Patient patient = new Patient(name, age, contactNumber);
        patient.setInsuranceInfo(insuranceInfo);
        return patient;
    }
}