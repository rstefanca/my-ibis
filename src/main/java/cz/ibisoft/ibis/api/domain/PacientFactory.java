package cz.ibisoft.ibis.api.domain;


/**
 * @author Richard Stefanca
 */
public class PacientFactory {

    private PacientFactory() {
        //utility class
    }

    /**
     * Factory metoda pro noveho pacienta
     * @param cp
     * @param jmena
     * @param prijmeni
     * @param email
     * @param mobil
     * @return
     */
    public static Pacient createNewPacient(String cp, String jmena, String prijmeni, String email, String mobil) {
        Pacient pacient = new Pacient(cp, jmena, prijmeni, new Kontakt(email, mobil));
        NastaveniUctu defaultNastaveniUctu = new NastaveniUctu("SMS", 1, "TEST", "MOBIL");
        defaultNastaveniUctu.setPacient(pacient);
        pacient.setNastaveniUctu(defaultNastaveniUctu);
        return pacient;
    }
}
