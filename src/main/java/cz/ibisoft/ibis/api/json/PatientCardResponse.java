package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public class PatientCardResponse {

    private final String code;
    private final String issuer;

    public PatientCardResponse(String code, String issuer) {
        this.code = code;
        this.issuer = issuer;
    }

    public String getCode() {
        return code;
    }

    public String getIssuer() {
        return issuer;
    }
}
