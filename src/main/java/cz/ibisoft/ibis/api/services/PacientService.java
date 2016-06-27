package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.model.Pacient;

import java.util.Optional;

/**
 * Interface pro praci s pacientem
 *
 * @author Richard Stefanca
 */
public interface PacientService {

    /**
     * Zalozeni pacienta
     *
     * @param pacient novy pacient
     */
    void create(Pacient pacient);

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     */
    Optional<Pacient> load(String id);
}
