package cz.ibisoft.ibis.api.protocol;

/**
 * @author Richard Stefanca
 */
public class Status {

    private final String status;

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
