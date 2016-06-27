package cz.ibisoft.ibis.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Richard Stefanca
 */

@Embeddable
public class NastaveniUctu {

    @Column(name = "PreferovanaKomunikace")
    private String preferovanaKomunikace;

    @Column(name = "DobaUchovaniIdentifikatoru")
    private Integer dobaUchovani;

    @Column(name = "PristupNaIdentifikatory")
    private String pristupNaIdentifikatory;

    @Column(name = "ZpusobPristupu")
    private String zpusobPristupu;

    @Column(name = "Heslo")
    private String heslo;

    @Column(name = "Zablokovany")
    private boolean zablokovany;


    protected NastaveniUctu() {
        //pro Hibernate
    }

    public NastaveniUctu(String preferovanaKomunikace, Integer dobaUchovani, String pristupNaIdentifikatory, String zpusobPristupu, String heslo) {
        this.preferovanaKomunikace = preferovanaKomunikace;
        this.dobaUchovani = dobaUchovani;
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
        this.zpusobPristupu = zpusobPristupu;
        this.heslo = heslo;
    }

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

    public boolean isZablokovany() {
        return zablokovany;
    }

    public void setZablokovany(boolean zablokovany) {
        this.zablokovany = zablokovany;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }
}
