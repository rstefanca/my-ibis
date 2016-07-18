package cz.ibisoft.ibis.api.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Richard Stefanca
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PacientNotFoundException extends RuntimeException {


    private final String pacientId;

    public PacientNotFoundException(String id) {
        super("customer#" + id + " was not found");
        this.pacientId = id;
    }

    public String getPacientId() {
        return pacientId;
    }
}
