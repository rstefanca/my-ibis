package cz.ibisoft.ibis.api.services.exceptions;

/**
 * @author Richard Stefanca
 */
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
