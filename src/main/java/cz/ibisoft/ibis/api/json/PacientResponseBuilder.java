package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public final class PacientResponseBuilder {
    private String cp;
    private String jmena;
    private String prijmeni;
    private Kontakt kontakt;
    private NastaveniUctu nastaveniUctu;
    private String otpKodProZalozeni;
    private String stavUctu;

    private PacientResponseBuilder() {
    }

    public static PacientResponseBuilder aPacientResponse() {
        return new PacientResponseBuilder();
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

    public PacientResponseBuilder withNastaveniUctu(NastaveniUctu nastaveniUctu) {
        this.nastaveniUctu = nastaveniUctu;
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
        pacientResponse.setCp(cp);
        pacientResponse.setJmena(jmena);
        pacientResponse.setPrijmeni(prijmeni);
        pacientResponse.setKontakt(kontakt);
        pacientResponse.setNastaveniUctu(nastaveniUctu);
        pacientResponse.setOtpKodProZalozeni(otpKodProZalozeni);
        pacientResponse.setStavUctu(stavUctu);
        return pacientResponse;
    }
}
