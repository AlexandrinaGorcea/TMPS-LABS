package domain.models.staff;
import java.util.List;
import java.util.ArrayList;

public class Doctor extends AbstractStaffMember {
    private final List<String> certifications;

    public Doctor(String name, String specialization) {
        super(name, specialization);
        this.certifications = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    public void addCertification(String certification) {
        certifications.add(certification);
    }

    public List<String> getCertifications() {
        return new ArrayList<>(certifications);
    }
}