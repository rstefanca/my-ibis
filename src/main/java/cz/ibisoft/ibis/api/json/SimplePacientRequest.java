package cz.ibisoft.ibis.api.json;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Richard Stefanca
 */
public class SimplePacientRequest {

    @Length(min = 9, max = 10)
    private String cp;

    @NotNull
    private String jmena;

    @NotNull
    private String prijmeni;

    private Kontakt kontakt;

    private String heslo;

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

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }
}
