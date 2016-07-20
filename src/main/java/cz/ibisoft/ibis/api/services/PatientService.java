package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.*;

/**
 * Interface pro praci s pacientem
 *
 * @author Richard Stefanca
 */
public interface PatientService {

    /**
     * Zalozeni pacienta
     * @param cp
     * @param jmena
     * @param prijmeni
     * @param email
     * @param mobile
     * @param heslo
     */
    Patient create(String cp, String jmena, String prijmeni, String email, String mobile, String heslo);

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     */
    Patient findById(String id);

    Patient update(String id, long version, String cp, String jmena, String prijmeni, String email, String mobile);

    AccountSetting updateAccountSettings(String guid, PreferredCommunication preferredCommunication, Integer dobaUchovani, PristupNaIdentifikatory pristupNaIdentifikatory, AccessType accessType);

    AccountSetting loadAccountSettings(String guid);
}
