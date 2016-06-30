package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.json.SimplePacientRequest;
import cz.ibisoft.ibis.api.model.Kontakt;
import cz.ibisoft.ibis.api.model.Pacient;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(Pacient pacient) {
        requireNonNull(pacient, "pacient cannot be null");
        pacientRepository.save(pacient);
    }

    @Override
    @Transactional
    public Optional<Pacient> save(String id, long version, SimplePacientRequest pacientRequest) {
        if (!pacientRepository.exists(id)) {
            throw new RuntimeException("Row doesn exists");
        }
        Pacient pacient = new Pacient(id, pacientRequest.getCp(), pacientRequest.getJmena(), pacientRequest.getPrijmeni(), new Kontakt("", ""));
        pacient.setVersion(version);
        Pacient saved = pacientRepository.save(pacient);
        return Optional.ofNullable(saved);
    }


}
