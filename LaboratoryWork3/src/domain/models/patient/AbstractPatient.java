package domain.models.patient;

public abstract class AbstractPatient implements IPatient {
    protected String id;
    protected String name;
    protected int age;
    protected String contactNumber;
    protected InsuranceInfo insuranceInfo;

    protected AbstractPatient(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.id = "PAT" + System.currentTimeMillis();
    }
}