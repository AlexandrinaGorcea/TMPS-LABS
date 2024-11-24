package domain.models.patient;

public interface IPatient {
    String getId();
    String getName();
    int getAge();
    String getContactNumber();
    InsuranceInfo getInsuranceInfo();
    void setInsuranceInfo(InsuranceInfo info);
}