package domain;
public abstract class AbstractPatient {
    protected String id;
    protected String name;
    protected int age;
    protected InsuranceInfo insuranceInfo;

    public abstract void setInsuranceInfo(InsuranceInfo info);
    public abstract String getId();
    public abstract String getName();
}