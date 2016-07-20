package cz.ibisoft.ibis.api.domain.exceptions;

import cz.ibisoft.ibis.api.domain.PatientCard;

/**
 * @author Richard Stefanca
 */
public class CardAlreadyAddedException extends RuntimeException {

    private final PatientCard patientCard;

    public CardAlreadyAddedException(PatientCard patientCard) {

        this.patientCard = patientCard;
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }
}
