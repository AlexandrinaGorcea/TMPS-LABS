package domain.models.patient;

public class Patient extends AbstractPatient {
    private InsuranceInfo insuranceInfo;

    public Patient(String name, int age, String contactNumber) {
        super(name, age, contactNumber);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public InsuranceInfo getInsuranceInfo() {  // Implement the getInsuranceInfo method
        return insuranceInfo;
    }

    @Override
    public void setInsuranceInfo(InsuranceInfo info) {  // Implement the setInsuranceInfo method
        this.insuranceInfo = info;
    }
}
