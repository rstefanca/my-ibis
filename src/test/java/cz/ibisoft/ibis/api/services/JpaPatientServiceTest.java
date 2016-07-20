package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.AccountSetting;
import cz.ibisoft.ibis.api.domain.Patient;
import cz.ibisoft.ibis.api.domain.PacientFactory;
import cz.ibisoft.ibis.api.repositories.AccountSettingRepository;
import cz.ibisoft.ibis.api.repositories.PatientRepository;
import cz.ibisoft.ibis.api.services.exceptions.PatientNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static cz.ibisoft.ibis.api.domain.PreferredCommunication.MOBILNI_APLIKACE;
import static cz.ibisoft.ibis.api.domain.PristupNaIdentifikatory.PREDEPSANE_VYDANE;
import static cz.ibisoft.ibis.api.domain.AccessType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Richard Stefanca
 */

@RunWith(MockitoJUnitRunner.class)
public class JpaPatientServiceTest {

    private static final String PACIENT_ID = UUID.randomUUID().toString();
    private static final String INSURANCE_NUMBER = "cp";
    private static final String FIRST_NAME = "jmena";
    private static final String LAST_NAME = "prijmeni";
    private static final String EMAIL = "email";
    private static final String MOBIL = "mobil";
    private static final String PASSWORD = "heslo";

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AccountSettingRepository accountSettingRepository;

    @InjectMocks
    private PatientService patientService = new JpaPatientService();


    @Test
    public void create_should_return_new_instance_of_Pacient() throws Exception {
        //when
        Patient patient = patientService.create(INSURANCE_NUMBER, FIRST_NAME, LAST_NAME, EMAIL, MOBIL, PASSWORD);

        //then
        assertNotNull(patient);
        assertEquals(INSURANCE_NUMBER, patient.getInsuranceNumber());
        assertEquals(FIRST_NAME, patient.getFirstName());
        assertEquals(LAST_NAME, patient.getLastName());
        assertNotNull(patient.getContact());
        assertEquals(EMAIL, patient.getContact().getEmail());
        assertEquals(MOBIL, patient.getContact().getMobil());
        assertEquals((Long) 0L, patient.getVersion());
        assertEquals(PASSWORD, patient.getPassword());

        //verify mocks
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    public void findById_should_return_pacient() throws Exception {
        //given
        Patient mockPatient = mockPacient("findById");

        //expect
        when(patientRepository.findOne(eq("1"))).thenReturn(mockPatient);

        //then
        Patient patient = patientService.findById("1");

        //then
        assertNotNull(patient);
        assertEquals(mockPatient, patient);

        //verify mocks
        verify(patientRepository, times(1)).findOne(eq("1"));

    }

    @Test(expected = PatientNotFoundException.class)
    public void findById_should_throw_PacientNotFoundException_when_pacient_not_found() throws Exception {
        try {
            //expect
            when(patientRepository.findOne(eq(PACIENT_ID))).thenReturn(null);

            //then
            patientService.findById(PACIENT_ID);

        } finally {
            //verify mocks
            verify(patientRepository).findOne(eq(PACIENT_ID));
        }
    }

    @Test
    public void update_should_return_updated_pacient() throws Exception {
        //given
        Patient mockPatient = mockPacient("update");

        //expect
        when(patientRepository.findOne(eq(PACIENT_ID))).thenReturn(mockPatient);

        //when
        Patient patient = patientService.update(PACIENT_ID, 0, INSURANCE_NUMBER, FIRST_NAME, LAST_NAME, EMAIL, MOBIL);

        //then
        assertNotNull(patient);
        assertEquals(mockPatient.getId(), patient.getId());
        assertEquals(INSURANCE_NUMBER, patient.getInsuranceNumber());
        assertEquals(FIRST_NAME, patient.getFirstName());
        assertEquals(LAST_NAME, patient.getLastName());
        assertNotNull(patient.getContact());
        assertEquals(EMAIL, patient.getContact().getEmail());
        assertEquals(MOBIL, patient.getContact().getMobil());

        //verify mocks
        verify(patientRepository, times(1)).findOne(eq(PACIENT_ID));
        verify(patientRepository, times(1)).update(eq(mockPatient));
    }

    @Test(expected = PatientNotFoundException.class)
    public void update_should_throw_pacient_not_found_exception_when_id_not_found() {
        //expect
        when(patientRepository.findOne(eq(PACIENT_ID))).thenReturn(null);
        try {
            //when
            patientService.update(PACIENT_ID, 0, INSURANCE_NUMBER, FIRST_NAME, LAST_NAME, EMAIL, MOBIL);
        } finally {
            //verify mocks
            verify(patientRepository, times(1)).findOne(eq(PACIENT_ID));
        }
    }

    @Test
    public void update_nastaveni_uctu_ok() {
        //given
        AccountSetting mockNastaveni = mockNastaveniUctu();

        //expect
        when(accountSettingRepository.findByPatientId(eq(PACIENT_ID))).thenReturn(mockNastaveni);

        //when
        AccountSetting accountSetting = patientService.updateAccountSettings(PACIENT_ID, MOBILNI_APLIKACE, 2, PREDEPSANE_VYDANE, KARTA_PACIENTA);

        //then
        assertEquals(MOBILNI_APLIKACE, accountSetting.getPreferredCommunication());
        assertEquals((Integer) 2, accountSetting.getDobaUchovani());
        assertEquals(PREDEPSANE_VYDANE, accountSetting.getPristupNaIdentifikatory());
        assertEquals(KARTA_PACIENTA, accountSetting.getAccessType());

        //verify mocks
        verify(accountSettingRepository, times(1)).findByPatientId(eq(PACIENT_ID));
    }

    @Test(expected = PatientNotFoundException.class)
    public void update_nastaveni_uctu_throws_pacient_not_found_exception_when_given_user_not_found() {
        //expect
        when(accountSettingRepository.findByPatientId(eq(PACIENT_ID))).thenReturn(null);

        try {
            //when
            patientService.updateAccountSettings(PACIENT_ID, MOBILNI_APLIKACE, 2, PREDEPSANE_VYDANE, KARTA_PACIENTA);
        } finally {
            //verify mocks
            verify(accountSettingRepository, times(1)).findByPatientId(eq(PACIENT_ID));
        }
    }

    /* Tovarny */

    private AccountSetting mockNastaveniUctu() {
        return new AccountSetting(MOBILNI_APLIKACE, 1, PREDEPSANE_VYDANE, KARTA_PACIENTA);
    }

    private static Patient mockPacient(String prefix) {
        return PacientFactory.createNewPacient(
                prefix + INSURANCE_NUMBER,
                prefix + FIRST_NAME,
                prefix + LAST_NAME,
                prefix + EMAIL,
                prefix + MOBIL,
                prefix + PASSWORD
        );
    }
}