package domain.models.patient;

public class InsuranceInfo {
    private final String policyNumber;
    private final String provider;
    private final String expiryDate;

    public InsuranceInfo(String policyNumber, String provider, String expiryDate) {
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.expiryDate = expiryDate;
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getProvider() { return provider; }
    public String getExpiryDate() { return expiryDate; }
}