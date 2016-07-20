package cz.ibisoft.ibis.api.domain;

import cz.ibisoft.ibis.api.domain.exceptions.CardAlreadyAddedException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Richard Stefanca
 */
public class PatientTest {

    @Test
    public void patient_should_add_cards() {
        //given
        Patient patient = getPatient();
        PatientCard patientCard1 = mock(PatientCard.class);
        PatientCard patientCard2 = mock(PatientCard.class);

        //when
        patient.addPatientCard(patientCard1);
        patient.addPatientCard(patientCard2);

        //then
        assertNotNull(patient.getPatientCards());
        assertEquals("Pacient by mel mit prirazeny dve karty", 2,patient.getPatientCards().size());
    }

    @Test(expected = CardAlreadyAddedException.class)
    public void patient_should_not_add_same_card_twice() {
        //given
        Patient patient = getPatient();
        PatientCard patientCard = mock(PatientCard.class);

        //when
        patient.addPatientCard(patientCard);
        patient.addPatientCard(patientCard);

        fail("Paceint nesmi mit prirazeny dve stejne karty");
    }

    @NotNull
    private Patient getPatient() {
        return new Patient("anyInsuranceNumber", "anyFirstName", "anyLastName", new Contact("anyEmail", "anyMobil"));
    }

}