package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.PatientCard;
import cz.ibisoft.ibis.api.repositories.PatientCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Richard Stefanca
 */

@RunWith(MockitoJUnitRunner.class)
public class JpaPatientCardServiceTest {

    private final String PATIENT_ID = UUID.randomUUID().toString();

    @Mock
    private PatientCardRepository patientCardRepository;

    @InjectMocks
    private PatientCardService patientService = new JpaPatientCardService();

    @Test
    public void should_return_patient_cards() {
        //given
        PatientCard mockPatientCard = mock(PatientCard.class);

        //expect
        when(patientCardRepository.findCardsByPatientId(eq(PATIENT_ID))).thenReturn(Collections.singletonList(mockPatientCard));

        //when
        List<PatientCard> patientCards = patientService.findCardsByPatientId(PATIENT_ID);


        //then
        assertNotNull("findCardsByPatientId nesmi vracet null", patientCards);
        assertEquals(mockPatientCard, patientCards.get(0));

        //verify
        verify(patientCardRepository, times(1)).findCardsByPatientId(eq(PATIENT_ID));
    }

    @Test
    public void should_return_empty_list_when_no_patient_cards_found() {
        //expect
        when(patientCardRepository.findCardsByPatientId(eq(PATIENT_ID))).thenReturn(Collections.emptyList());

        //when
        List<PatientCard> patientCards = patientService.findCardsByPatientId(PATIENT_ID);

        //then
        assertNotNull("findCardsByPatientId nesmi vracet null", patientCards);
        assertTrue("Kolekce musi prazdna, pokud neni nalezena zadna karta", patientCards.isEmpty());

        //verify
        verify(patientCardRepository, times(1)).findCardsByPatientId(eq(PATIENT_ID));

    }




}
