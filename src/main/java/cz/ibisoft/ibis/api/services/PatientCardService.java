package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.PatientCard;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Richard Stefanca
 */
public interface PatientCardService {

    /**
     * Vraci vsechny karty pacienta
     * @param patientId id pacienta
     * @return karty pacienta
     */
    @NotNull
    List<PatientCard> findCardsByPatientId(@NotNull String patientId);

}
