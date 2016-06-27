package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public class NastaveniUctu {

    private String preferovanaKomunikace;
    private Integer dobaUchovani;
    private String pristupNaIdentifikatory;
    private String zpusobPristupu;

    public String getPreferovanaKomunikace() {
        return preferovanaKomunikace;
    }

    public void setPreferovanaKomunikace(String preferovanaKomunikace) {
        this.preferovanaKomunikace = preferovanaKomunikace;
    }

    public Integer getDobaUchovani() {
        return dobaUchovani;
    }

    public void setDobaUchovani(Integer dobaUchovani) {
        this.dobaUchovani = dobaUchovani;
    }

    public String getPristupNaIdentifikatory() {
        return pristupNaIdentifikatory;
    }

    public void setPristupNaIdentifikatory(String pristupNaIdentifikatory) {
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
    }

    public String getZpusobPristupu() {
        return zpusobPristupu;
    }

    public void setZpusobPristupu(String zpusobPristupu) {
        this.zpusobPristupu = zpusobPristupu;
    }


}
