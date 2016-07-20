package cz.ibisoft.ibis.api.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Richard Stefanca
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AccountSetting extends BaseEntity {

    @Column(name = "PreferredCommunication")
    @Enumerated(EnumType.STRING)
    private PreferredCommunication preferredCommunication;

    @Column(name = "DobaUchovaniIdentifikatoru")
    private Integer dobaUchovani;

    @Column(name = "PristupNaIdentifikatory")
    @Enumerated(EnumType.STRING)
    private PristupNaIdentifikatory pristupNaIdentifikatory;

    @Column(name = "AccessType")
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;


    protected AccountSetting() {
        //pro Hibernate
    }

    public AccountSetting(PreferredCommunication preferredCommunication, Integer dobaUchovani, PristupNaIdentifikatory pristupNaIdentifikatory, AccessType accessType) {
        this.preferredCommunication = preferredCommunication;
        this.dobaUchovani = dobaUchovani;
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
        this.accessType = accessType;
    }

    public PreferredCommunication getPreferredCommunication() {
        return preferredCommunication;
    }

    public void setPreferredCommunication(PreferredCommunication preferredCommunication) {
        this.preferredCommunication = preferredCommunication;
    }

    public Integer getDobaUchovani() {
        return dobaUchovani;
    }

    public void setDobaUchovani(Integer dobaUchovani) {
        this.dobaUchovani = dobaUchovani;
    }

    public PristupNaIdentifikatory getPristupNaIdentifikatory() {
        return pristupNaIdentifikatory;
    }

    public void setPristupNaIdentifikatory(PristupNaIdentifikatory pristupNaIdentifikatory) {
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
