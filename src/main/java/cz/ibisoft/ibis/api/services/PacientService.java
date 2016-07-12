package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.json.SimplePacientRequest;
import cz.ibisoft.ibis.api.domain.Pacient;

import java.util.Optional;

/**
 * Interface pro praci s pacientem
 *
 * @author Richard Stefanca
 */
public interface PacientService {

    /**
     * Zalozeni pacienta
     * @param cp
     * @param jmena
     * @param prijmeni
     * @param email
     * @param mobile
     */
    Pacient create(String cp, String jmena, String prijmeni, String email, String mobile);

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     */
    Pacient findById(String id);

    Pacient update(String id, long version, String cp, String jmena, String prijmeni, String email, String mobile);

    NastaveniUctu updateNastaveniUctu(String guid, String preferovanaKomunikace, Integer dobaUchovani, String pristupNaIdentifikatory, String zpusobPristupu);

    NastaveniUctu loadPacientNastaveniUctu(String guid);
}
