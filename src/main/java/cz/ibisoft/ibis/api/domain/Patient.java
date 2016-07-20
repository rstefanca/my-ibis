package cz.ibisoft.ibis.api.domain;

import cz.ibisoft.ibis.api.domain.exceptions.CardAlreadyAddedException;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * @author Richard Stefanca
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Patient extends BaseEntity {

    @Column(name = "CP")
    private String insuranceNumber;

    @Column(name = "Jmena")
    private String firstName;

    @Column(name = "Prijmeni")
    private String lastName;

    @Column(name = "Heslo")
    private String password;

    @Column(name = "Zablokovany")
    private boolean blocked;

    @Embedded
    private Contact contact;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
    private AccountSetting accountSetting;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<PatientCard> patientCards = new HashSet<>();

    protected Patient() {

    }

    protected Patient(String insuranceNumber, String firstName, String lastName, Contact contact) {
        this.insuranceNumber = requireNonNull(insuranceNumber, "insuranceNumber cannot be null");
        this.firstName = requireNonNull(firstName, "firstName cannot be null");
        this.lastName = requireNonNull(lastName, "firstName cannot be null");
        this.contact = requireNonNull(contact, "contact cannot be null");
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public AccountSetting getAccountSetting() {
        return accountSetting;
    }

    public void setAccountSetting(AccountSetting accountSetting) {
        this.accountSetting = accountSetting;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isActive() {
        return !blocked;
    }

    public Set<PatientCard> getPatientCards() {
        return patientCards;
    }

    public void addPatientCard(PatientCard patientCard) {
        if (!patientCards.add(patientCard)) {
            throw new CardAlreadyAddedException(patientCard);
        }
    }
}
