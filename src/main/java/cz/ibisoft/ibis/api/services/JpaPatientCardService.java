package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.PatientCard;
import cz.ibisoft.ibis.api.repositories.PatientCardRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Richard Stefanca
 */
@Service
public class JpaPatientCardService implements PatientCardService {

    @Autowired
    private PatientCardRepository patientCardRepository;
    /**
     * Vraci vsechny karty pacienta
     *
     * @param patientId id pacienta
     * @return karty pacienta
     */
    @NotNull
    @Override
    public List<PatientCard> findCardsByPatientId(@NotNull String patientId) {
        return Collections.unmodifiableList(patientCardRepository.findCardsByPatientId(patientId));
    }
}
