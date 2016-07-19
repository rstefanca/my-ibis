package cz.ibisoft.ibis.api.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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

    @Column(name = "Heslo")
    private String heslo;

    @Column(name = "Zablokovany")
    private boolean zablokovany;

    @Embedded
    private Kontakt kontakt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pacient", cascade = CascadeType.ALL)
    private NastaveniUctu nastaveniUctu;

    protected Pacient() {

    }

    protected Pacient(String cp, String jmena, String prijmeni, Kontakt kontakt) {
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

    public NastaveniUctu getNastaveniUctu() {
        return nastaveniUctu;
    }

    public void setNastaveniUctu(NastaveniUctu nastaveniUctu) {
        this.nastaveniUctu = nastaveniUctu;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public boolean isZablokovany() {
        return zablokovany;
    }

    public void setZablokovany(boolean zablokovany) {
        this.zablokovany = zablokovany;
    }
}
