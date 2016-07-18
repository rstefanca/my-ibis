package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public class PacientResponse {

    private String id;
    private String cp;
    private String jmena;
    private String prijmeni;
    private Kontakt kontakt;
    private NastaveniUctuResponse nastaveniUctu;
    private String otpKodProZalozeni;
    private String stavUctu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public NastaveniUctuResponse getNastaveniUctu() {
        return nastaveniUctu;
    }

    public void setNastaveniUctu(NastaveniUctuResponse nastaveniUctu) {
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


    public static final class PacientResponseBuilder {
        private String id;
        private String cp;
        private String jmena;
        private String prijmeni;
        private Kontakt kontakt;
        private NastaveniUctuResponse nastaveniUctuResponse;
        private String otpKodProZalozeni;
        private String stavUctu;

        private PacientResponseBuilder() {
        }

        public static PacientResponseBuilder aPacientResponse() {
            return new PacientResponseBuilder();
        }

        public PacientResponseBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public PacientResponseBuilder withCp(String cp) {
            this.cp = cp;
            return this;
        }

        public PacientResponseBuilder withJmena(String jmena) {
            this.jmena = jmena;
            return this;
        }

        public PacientResponseBuilder withPrijmeni(String prijmeni) {
            this.prijmeni = prijmeni;
            return this;
        }

        public PacientResponseBuilder withKontakt(Kontakt kontakt) {
            this.kontakt = kontakt;
            return this;
        }

        public PacientResponseBuilder withNastaveniUctu(NastaveniUctuResponse nastaveniUctuResponse) {
            this.nastaveniUctuResponse = nastaveniUctuResponse;
            return this;
        }

        public PacientResponseBuilder withOtpKodProZalozeni(String otpKodProZalozeni) {
            this.otpKodProZalozeni = otpKodProZalozeni;
            return this;
        }

        public PacientResponseBuilder withStavUctu(String stavUctu) {
            this.stavUctu = stavUctu;
            return this;
        }

        public PacientResponse build() {
            PacientResponse pacientResponse = new PacientResponse();
            pacientResponse.setId(id);
            pacientResponse.setCp(cp);
            pacientResponse.setJmena(jmena);
            pacientResponse.setPrijmeni(prijmeni);
            pacientResponse.setKontakt(kontakt);
            pacientResponse.setNastaveniUctu(nastaveniUctuResponse);
            pacientResponse.setOtpKodProZalozeni(otpKodProZalozeni);
            pacientResponse.setStavUctu(stavUctu);
            return pacientResponse;
        }
    }
}
