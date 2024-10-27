package domain;
import util.*;
public class Patient extends AbstractPatient {
    private String contactNumber;

    public Patient(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.id = IdGenerator.getInstance().generatePatientId();
    }

    @Override
    public void setInsuranceInfo(InsuranceInfo info) {
        this.insuranceInfo = info;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}