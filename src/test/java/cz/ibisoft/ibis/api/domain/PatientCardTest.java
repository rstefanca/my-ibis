package cz.ibisoft.ibis.api.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Richard Stefanca
 */
public class PatientCardTest {

    private static final String ANY_CODE = "123456";
    private static final String ANY_ISSUER = "test";

    @Test
    public void factory_method_should_return_card_with_patient() throws Exception {
        //given
        Patient mockPatient = mock(Patient.class);

        //when
        PatientCard patientCard = PatientCard.createPatientCard(mockPatient, ANY_CODE, ANY_ISSUER);

        //then
        assertNotNull("Factory metoda musi vracet instanci", patientCard);
        assertEquals("Factory metoda musi nasetovat pacienta" , mockPatient, patientCard.getPatient());
    }

    @Test
    public void cards_should_equal_when_patient_code_issuer_are_the_same() {
        //given
        Patient mockPatient = mock(Patient.class);
        PatientCard patientCard1 = PatientCard.createPatientCard(mockPatient, ANY_CODE, ANY_ISSUER);
        PatientCard patientCard2 = PatientCard.createPatientCard(mockPatient, ANY_CODE, ANY_ISSUER);

        //then
        assertEquals(patientCard1, patientCard2);
    }

    @Test
    public void cards_should_not_equal_when_patient_is_different() {
        //given
        Patient mockPatient1 = mock(Patient.class);
        Patient mockPatient2 = mock(Patient.class);
        PatientCard patientCard1 = PatientCard.createPatientCard(mockPatient1, ANY_CODE, ANY_ISSUER);
        PatientCard patientCard2 = PatientCard.createPatientCard(mockPatient2, ANY_CODE, ANY_ISSUER);

        //then
        assertNotEquals(patientCard1, patientCard2);
    }

}