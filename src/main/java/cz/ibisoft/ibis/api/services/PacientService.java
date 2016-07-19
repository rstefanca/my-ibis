package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.*;
import cz.ibisoft.ibis.api.json.SimplePacientRequest;

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
     * @param heslo
     */
    Pacient create(String cp, String jmena, String prijmeni, String email, String mobile, String heslo);

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     */
    Pacient findById(String id);

    Pacient update(String id, long version, String cp, String jmena, String prijmeni, String email, String mobile);

    NastaveniUctu updateNastaveniUctu(String guid, PreferovanaKomunikace preferovanaKomunikace, Integer dobaUchovani, PristupNaIdentifikatory pristupNaIdentifikatory, ZpusobPristupu zpusobPristupu);

    NastaveniUctu loadPacientNastaveniUctu(String guid);
}
