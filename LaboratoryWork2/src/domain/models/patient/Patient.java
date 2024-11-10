package domain.models.patient;

public class Patient extends AbstractPatient {
    public Patient(String name, int age, String contactNumber, InsuranceInfo insurance) {
        super(name, age, contactNumber);
        this.insuranceInfo = insurance;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public int getAge() { return age; }

    @Override
    public String getContactNumber() { return contactNumber; }

    @Override
    public InsuranceInfo getInsuranceInfo() { return insuranceInfo; }

    @Override
    public void setInsuranceInfo(InsuranceInfo info) { this.insuranceInfo = info; }
}