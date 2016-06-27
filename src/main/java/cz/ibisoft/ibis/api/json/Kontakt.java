package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public class Kontakt {
    private  String email;
    private  String mobil;

    public Kontakt() {
        //default konstruktor pro Jacskon
    }

    public Kontakt(String email, String mobil) {
        this.email = email;
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public String getMobil() {
        return mobil;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }
}
