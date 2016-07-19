package cz.ibisoft.ibis.api.domain;


import static cz.ibisoft.ibis.api.domain.PreferovanaKomunikace.*;
import static cz.ibisoft.ibis.api.domain.PristupNaIdentifikatory.*;
import static cz.ibisoft.ibis.api.domain.ZpusobPristupu.*;

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
     * @return instance pacienta
     */
    public static Pacient createNewPacient(String cp, String jmena, String prijmeni, String email, String mobil, String heslo) {
        Pacient pacient = new Pacient(cp, jmena, prijmeni, new Kontakt(email, mobil));
        NastaveniUctu defaultNastaveniUctu = new NastaveniUctu(MOBILNI_APLIKACE, 1, PREDEPSANE_VYDANE, KARTA_PACIENTA);
        defaultNastaveniUctu.setPacient(pacient);
        pacient.setNastaveniUctu(defaultNastaveniUctu);
        pacient.setHeslo(heslo);
        return pacient;
    }
}
