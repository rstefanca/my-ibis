package cz.ibisoft.ibis.api.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Richard Stefanca
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class NastaveniUctu extends BaseEntity {



    @Column(name = "PreferovanaKomunikace")
    @Enumerated(EnumType.STRING)
    private PreferovanaKomunikace preferovanaKomunikace;

    @Column(name = "DobaUchovaniIdentifikatoru")
    private Integer dobaUchovani;

    @Column(name = "PristupNaIdentifikatory")
    @Enumerated(EnumType.STRING)
    private PristupNaIdentifikatory pristupNaIdentifikatory;

    @Column(name = "ZpusobPristupu")
    @Enumerated(EnumType.STRING)
    private ZpusobPristupu zpusobPristupu;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_id", referencedColumnName = "id", nullable = false)
    private Pacient pacient;


    protected NastaveniUctu() {
        //pro Hibernate
    }

    public NastaveniUctu(PreferovanaKomunikace preferovanaKomunikace, Integer dobaUchovani, PristupNaIdentifikatory pristupNaIdentifikatory, ZpusobPristupu zpusobPristupu) {
        this.preferovanaKomunikace = preferovanaKomunikace;
        this.dobaUchovani = dobaUchovani;
        this.pristupNaIdentifikatory = pristupNaIdentifikatory;
        this.zpusobPristupu = zpusobPristupu;
    }

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

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
}
