package cz.ibisoft.ibis.api.domain;


import static cz.ibisoft.ibis.api.domain.PreferredCommunication.*;
import static cz.ibisoft.ibis.api.domain.PristupNaIdentifikatory.*;
import static cz.ibisoft.ibis.api.domain.AccessType.*;

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
    public static Patient createNewPacient(String cp, String jmena, String prijmeni, String email, String mobil, String heslo) {
        Patient patient = new Patient(cp, jmena, prijmeni, new Contact(email, mobil));
        AccountSetting defaultAccountSetting = new AccountSetting(MOBILNI_APLIKACE, 1, PREDEPSANE_VYDANE, KARTA_PACIENTA);
        defaultAccountSetting.setPatient(patient);
        patient.setAccountSetting(defaultAccountSetting);
        patient.setPassword(heslo);
        return patient;
    }
}
