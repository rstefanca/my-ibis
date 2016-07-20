package cz.ibisoft.ibis.api.domain;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entita pro kartu pacienta
 *
 * @author Richard Stefanca
 */

@Entity
public class PatientCard {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column
    private String code;

    @Column
    private String issuer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pacient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    protected PatientCard() {
        // fore sake of Hibernate
    }

    protected PatientCard(String code, String issuer) {
        this.code = code;
        this.issuer = issuer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @NotNull
    public static PatientCard createPatientCard(@NotNull Patient patient, @NotNull String code, @NotNull String issuer) {
        PatientCard patientCard = new PatientCard(code, issuer);
        patientCard.setPatient(patient);
        return patientCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientCard that = (PatientCard) o;

        if (!code.equals(that.code)) return false;
        if (!issuer.equals(that.issuer)) return false;
        return patient.equals(that.patient);

    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + issuer.hashCode();
        result = 31 * result + patient.hashCode();
        return result;
    }
}
