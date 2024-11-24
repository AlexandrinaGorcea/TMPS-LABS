package service;

import domain.models.patient.Patient;
import domain.models.patient.InsuranceInfo;
import domain.factory.PatientFactory;
import util.singleton.HospitalSystem;
import util.singleton.Logger;

public class PatientService {
    private final HospitalSystem hospitalSystem;
    private final Logger logger;

    public PatientService() {
        this.hospitalSystem = HospitalSystem.getInstance();
        this.logger = Logger.getInstance();
    }

    public Patient registerPatient(String name, int age, String contactNumber, InsuranceInfo insurance) {
        Patient patient = PatientFactory.createPatient(name, age, contactNumber, insurance);
        hospitalSystem.addPatient(patient);
        logger.log("Registered new patient: " + patient.getId());
        return patient;
    }

    public void updatePatientInsurance(String patientId, InsuranceInfo newInsurance) {
        Patient patient = hospitalSystem.getPatient(patientId);
        if (patient != null) {
            patient.setInsuranceInfo(newInsurance);
            logger.log("Updated insurance for patient: " + patientId);
        }
    }
}
