package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.domain.PacientFactory;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
import cz.ibisoft.ibis.api.services.exceptions.PacientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Implementace {@link PacientService}
 *
 * @author Richard Stefanca
 */
@Service
public class JpaPacientService implements PacientService {

    @Autowired
    private PacientRepository pacientRepository;

    /**
     * Zalozeni pacienta
     *
     * @param cp
     * @param jmena
     * @param prijmeni
     * @param email
     * @param mobile
     */
    @Override
    @Transactional
    public Pacient create(String cp, String jmena, String prijmeni, String email, String mobile) {
        Pacient pacient = PacientFactory.createNewPacient(cp, jmena, prijmeni, email, mobile);
        pacientRepository.update(pacient);
        return pacient;
    }

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     * @throws PacientNotFoundException pokud neni pacient nalezen
     */
    @Override
    public Pacient findById(String id) {
        Objects.requireNonNull(id, "Parameter id cannot be null");
        Pacient pacient = pacientRepository.findOne(id);
        if (pacient == null) {
            throw new PacientNotFoundException(id);
        }

        return pacient;
    }

    @Override
    public Pacient update(String id, long version, String cp, String jmena, String prijmeni, String email, String mobile) {
        Pacient pacient = findById(id);
        pacient.setVersion(version);
        pacient.setCp(cp);
        pacient.setJmena(jmena);
        pacient.setPrijmeni(prijmeni);
        pacient.getKontakt().setEmail(email);
        pacient.getKontakt().setMobil(mobile);
        pacientRepository.update(pacient);
        return pacient;
    }

    @Override
    @Transactional
    public NastaveniUctu updateNastaveniUctu(String guid, String preferovanaKomunikace, Integer dobaUchovani, String pristupNaIdentifikatory, String zpusobPristupu) {
        return null;
    }

    @Override
    public NastaveniUctu loadPacientNastaveniUctu(String guid) {
        throw new UnsupportedOperationException("Neumime");
    }
}
