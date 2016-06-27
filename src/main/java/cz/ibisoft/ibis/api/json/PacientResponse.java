package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public class PacientResponse {

    private String cp;
    private String jmena;
    private String prijmeni;
    private Kontakt kontakt;
    private NastaveniUctu nastaveniUctu;
    private String otpKodProZalozeni;
    private String stavUctu;


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

    public String getOtpKodProZalozeni() {
        return otpKodProZalozeni;
    }

    public void setOtpKodProZalozeni(String otpKodProZalozeni) {
        this.otpKodProZalozeni = otpKodProZalozeni;
    }

    public String getStavUctu() {
        return stavUctu;
    }

    public void setStavUctu(String stavUctu) {
        this.stavUctu = stavUctu;
    }




}
