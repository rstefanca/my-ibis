package cz.ibisoft.ibis.api.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Richard Stefanca
 */
public class PatientFactoryTest {

    private static final String ANY_INSURANCE_NUMBER = "cp";
    private static final String ANY_FIRST_NAME = "jmena";
    private static final String ANY_LAST_NAME = "prijmeni";
    private static final String ANY_EMAIL = "email";
    private static final String ANY_MOBIL = "mobil";
    private static final String ANY_PASSWORD = "heslo";

    private static final PreferredCommunication PREFEROVANA_KOMUNIKACE_DEFAULT = PreferredCommunication.MOBILNI_APLIKACE;


    @Test
    public void factory_method_should_create_new_pacient_with_default_settings()  {
        //when
        Patient patient = PacientFactory.createNewPacient(ANY_INSURANCE_NUMBER, ANY_FIRST_NAME, ANY_LAST_NAME, ANY_EMAIL, ANY_MOBIL, ANY_PASSWORD);

        //then
        assertNotNull( "Patient nesmi byt null", patient);
        assertNotNull("Factory metoda musi vytvorit instanci tridi Contact", patient.getContact());
        assertNotNull("Patient musi mit defaultni nastaveni uctu", patient.getAccountSetting());

        assertEquals(ANY_INSURANCE_NUMBER, patient.getInsuranceNumber());
        assertEquals(ANY_FIRST_NAME, patient.getFirstName());
        assertEquals(ANY_LAST_NAME, patient.getLastName());
        assertEquals(ANY_EMAIL, patient.getContact().getEmail());
        assertEquals(ANY_MOBIL, patient.getContact().getMobil());
        assertEquals(ANY_PASSWORD, patient.getPassword());
        assertFalse("Patient musi byt vytvoren jako nezablokovany", patient.isBlocked());
        assertEquals(PREFEROVANA_KOMUNIKACE_DEFAULT, patient.getAccountSetting().getPreferredCommunication());
    }
}