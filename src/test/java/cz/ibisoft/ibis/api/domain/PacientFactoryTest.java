package cz.ibisoft.ibis.api.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Richard Stefanca
 */
public class PacientFactoryTest {

    private static final String CP = "cp";
    private static final String JMENA = "jmena";
    private static final String PRIJMENI = "prijmeni";
    private static final String EMAIL = "email";
    private static final String MOBIL = "mobil";
    private static final String HESLO = "heslo";

    private static final PreferovanaKomunikace PREFEROVANA_KOMUNIKACE_DEFAULT = PreferovanaKomunikace.MOBILNI_APLIKACE;


    @Test
    public void factory_method_should_create_new_pacient_with_default_settings()  {
        //when
        Pacient pacient = PacientFactory.createNewPacient(CP, JMENA, PRIJMENI, EMAIL, MOBIL, HESLO);

        //then
        assertNotNull( "Pacient nesmi byt null",pacient);
        assertNotNull("Factory metoda musi vytvorit instanci tridi Kontakt", pacient.getKontakt());
        assertNotNull("Pacient musi mit defaultni nastaveni uctu", pacient.getNastaveniUctu());

        assertEquals(CP, pacient.getCp());
        assertEquals(JMENA, pacient.getJmena());
        assertEquals(PRIJMENI, pacient.getPrijmeni());
        assertEquals(EMAIL, pacient.getKontakt().getEmail());
        assertEquals(MOBIL, pacient.getKontakt().getMobil());
        assertEquals(HESLO, pacient.getHeslo());
        assertFalse("Pacient musi byt vytvoren jako nezablokovany", pacient.isZablokovany());
        assertEquals(PREFEROVANA_KOMUNIKACE_DEFAULT, pacient.getNastaveniUctu().getPreferovanaKomunikace());
    }
}