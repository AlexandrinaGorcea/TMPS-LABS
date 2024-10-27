package domain;
public class InsuranceInfo {
    private String policyNumber;
    private String provider;
    private String expiryDate;

    public InsuranceInfo(String policyNumber, String provider, String expiryDate) {
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.expiryDate = expiryDate;
    }

    // Getters
    public String getPolicyNumber() { return policyNumber; }
    public String getProvider() { return provider; }
    public String getExpiryDate() { return expiryDate; }
}
