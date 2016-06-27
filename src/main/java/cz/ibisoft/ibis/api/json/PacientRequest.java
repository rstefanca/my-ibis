package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public class PacientRequest {

    private String cp;
    private String jmena;
    private String prijmeni;
    private Kontakt kontakt;
    private Hesla hesla;
    private NastaveniUctu nastaveniUctu;

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

    public Hesla getHesla() {
        return hesla;
    }

    public void setHesla(Hesla hesla) {
        this.hesla = hesla;
    }

    public NastaveniUctu getNastaveniUctu() {
        return nastaveniUctu;
    }

    public void setNastaveniUctu(NastaveniUctu nastaveniUctu) {
        this.nastaveniUctu = nastaveniUctu;
    }
}
