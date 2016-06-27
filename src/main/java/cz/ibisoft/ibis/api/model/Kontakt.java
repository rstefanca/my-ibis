package cz.ibisoft.ibis.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Richard Stefanca
 */

@Embeddable
public class Kontakt {

    @Column
    private String email;

    @Column
    private String mobil;

    protected Kontakt() {
        //pro Hibernate
    }

    public Kontakt(String email, String mobil) {
        this.email = email;
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }
}
