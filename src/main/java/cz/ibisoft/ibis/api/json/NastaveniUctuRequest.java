package cz.ibisoft.ibis.api.json;

import cz.ibisoft.ibis.api.domain.PreferovanaKomunikace;
import cz.ibisoft.ibis.api.domain.PristupNaIdentifikatory;
import cz.ibisoft.ibis.api.domain.ZpusobPristupu;

/**
 * @author Richard Stefanca
 */
public class NastaveniUctuRequest {

    private PreferovanaKomunikace preferovanaKomunikace;


    private Integer dobaUchovani;


    private PristupNaIdentifikatory pristupNaIdentifikatory;

    private ZpusobPristupu zpusobPristupu;

    public PreferovanaKomunikace getPreferovanaKomunikace() {
        return preferovanaKomunikace;
    }

    public void setPreferovanaKomunikace(PreferovanaKomunikace preferovanaKomunikace) {
        this.preferovanaKomunikace = preferovanaKomunikace;
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

    public ZpusobPristupu getZpusobPristupu() {
        return zpusobPristupu;
    }

    public void setZpusobPristupu(ZpusobPristupu zpusobPristupu) {
        this.zpusobPristupu = zpusobPristupu;
    }
}
