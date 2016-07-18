package cz.ibisoft.ibis.api.json;

/**
 * @author Richard Stefanca
 */
public final class NastaveniUctuBuilder {
    private String preferovanaKomunikace;
    private Integer dobaUchovani;
    private String pristupNaIdentifikatory;
    private String zpusobPristupu;

    private NastaveniUctuBuilder() {
    }

    public static NastaveniUctuBuilder aNastaveniUctu() {
        return new NastaveniUctuBuilder();
    }

    public NastaveniUctuBuilder withPreferovanaKomunikace(String preferovanaKomunikace) {
        this.preferovanaKomunikace = preferovanaKomunikace;
        return this;
    }

    public NastaveniUctuBuilder withDobaUchovani(Integer dobaUchovani) {
        this.dobaUchovani = dobaUchovani;
        return this;
    }

    public NastaveniUctuBuilder withPristupNaIdentifikatory(String pristupNaIdentifikatory) {
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
        return this;
    }

    public NastaveniUctuBuilder withZpusobPristupu(String zpusobPristupu) {
        this.zpusobPristupu = zpusobPristupu;
        return this;
    }

    public NastaveniUctuResponse build() {
        NastaveniUctuResponse nastaveniUctuResponse = new NastaveniUctuResponse();
        nastaveniUctuResponse.setPreferovanaKomunikace(preferovanaKomunikace);
        nastaveniUctuResponse.setDobaUchovani(dobaUchovani);
        nastaveniUctuResponse.setPristupNaIdentifikatory(pristupNaIdentifikatory);
        nastaveniUctuResponse.setZpusobPristupu(zpusobPristupu);
        return nastaveniUctuResponse;
    }
}
