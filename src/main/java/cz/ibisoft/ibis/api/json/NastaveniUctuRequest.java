package cz.ibisoft.ibis.api.json;

import cz.ibisoft.ibis.api.domain.AccessType;
import cz.ibisoft.ibis.api.domain.PreferredCommunication;
import cz.ibisoft.ibis.api.domain.PristupNaIdentifikatory;

/**
 * @author Richard Stefanca
 */
public class NastaveniUctuRequest {

    private PreferredCommunication preferredCommunication;


    private Integer dobaUchovani;


    private PristupNaIdentifikatory pristupNaIdentifikatory;

    private AccessType accessType;

    public PreferredCommunication getPreferredCommunication() {
        return preferredCommunication;
    }

    public void setPreferredCommunication(PreferredCommunication preferredCommunication) {
        this.preferredCommunication = preferredCommunication;
    }

    public Integer getDobaUchovani() {
        return dobaUchovani;
    }

    public void setDobaUchovani(Integer dobaUchovani) {
        this.dobaUchovani = dobaUchovani;
    }

    public PristupNaIdentifikatory getPristupNaIdentifikatory() {
        return pristupNaIdentifikatory;
    }

    public void setPristupNaIdentifikatory(PristupNaIdentifikatory pristupNaIdentifikatory) {
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }
}
