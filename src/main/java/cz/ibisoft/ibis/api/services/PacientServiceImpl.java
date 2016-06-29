package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.model.Pacient;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.*;

/**
 * Implementace {@link PacientService}
 *
 * @author Richard Stefanca
 */
@Service
public class PacientServiceImpl implements PacientService {

    @Autowired
    private PacientRepository pacientRepository;

    /**
     * Zalozeni pacienta
     *
     * @param pacient novy pacient
     */
    @Override
    @Transactional
    public void create(Pacient pacient) {
        requireNonNull(pacient, "pacient cannot be null");
        pacientRepository.save(pacient);
    }

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     */
    @Override
    public Optional<Pacient> load(String id) {
        return pacientRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Pacient pacient) {
        requireNonNull(pacient, "pacient cannot be null");
        pacientRepository.save(pacient);
    }


}
