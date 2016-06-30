package cz.ibisoft.ibis.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * @author Richard Stefanca
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pacient extends BaseEntity {

    @Column(name = "CP")
    private String cp;

    @Column(name = "Jmena")
    private String jmena;

    @Column(name = "Prijmeni")
    private String prijmeni;

    @Embedded
    private Kontakt kontakt;


   /* //todo 1:1
    private NastaveniUctu nastaveniUctu;*/

    public Pacient() {

    }

    public Pacient(String id, String cp, String jmena, String prijmeni, Kontakt kontakt) {
        this.id = id;
        this.cp = requireNonNull(cp, "cp cannot be null");
        this.jmena = requireNonNull(jmena, "jmena cannot be null");
        this.prijmeni = requireNonNull(prijmeni, "jmena cannot be null");
        this.kontakt = requireNonNull(kontakt, "kontakt cannot be null");

    }


    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getJmena() {
        return jmena;
    }

    public void setJmena(String jmena) {
        this.jmena = jmena;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }
}
