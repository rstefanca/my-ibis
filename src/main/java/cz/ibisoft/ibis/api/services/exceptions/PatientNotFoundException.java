package cz.ibisoft.ibis.api.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Richard Stefanca
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

    private final String patientId;

    public PatientNotFoundException(String patientId) {
        super("Patient #" + patientId + " was not found");
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }
}
