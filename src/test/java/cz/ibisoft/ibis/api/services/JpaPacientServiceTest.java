package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.Kontakt;
import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.domain.PacientFactory;
import cz.ibisoft.ibis.api.repositories.NastaveniUctuRepository;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
import cz.ibisoft.ibis.api.services.exceptions.PacientNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static cz.ibisoft.ibis.api.domain.PreferovanaKomunikace.MOBILNI_APLIKACE;
import static cz.ibisoft.ibis.api.domain.PristupNaIdentifikatory.PREDEPSANE_VYDANE;
import static cz.ibisoft.ibis.api.domain.ZpusobPristupu.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Richard Stefanca
 */

@RunWith(MockitoJUnitRunner.class)
public class JpaPacientServiceTest {

    private static final String CP = "cp";
    private static final String JMENA = "jmena";
    private static final String PRIJMENI = "prijmeni";
    private static final String EMAIL = "email";
    private static final String MOBIL = "mobil";
    private static final String HESLO = "heslo";
    @Mock
    private PacientRepository pacientRepository;

    @Mock
    private NastaveniUctuRepository nastaveniUctuRepository;

    @InjectMocks
    private PacientService pacientService = new JpaPacientService();
    private static final String PACIENT_ID = "1";

    @Test
    public void create_should_return_new_instance_of_Pacient() throws Exception {
        //when
        Pacient pacient = pacientService.create(CP, JMENA, PRIJMENI, EMAIL, MOBIL, HESLO);

        //then
        assertNotNull(pacient);
        assertEquals(CP, pacient.getCp());
        assertEquals(JMENA, pacient.getJmena());
        assertEquals(PRIJMENI, pacient.getPrijmeni());
        assertNotNull(pacient.getKontakt());
        assertEquals(EMAIL, pacient.getKontakt().getEmail());
        assertEquals(MOBIL, pacient.getKontakt().getMobil());
        assertEquals((Long) 0L, pacient.getVersion());
        assertEquals(HESLO, pacient.getHeslo());

        //verify mocks
        verify(pacientRepository).save(any(Pacient.class));
    }

    @Test
    public void findById_should_return_pacient() throws Exception {
        //given
        Pacient mockPacient = mockPacient("findById");

        //expect
        when(pacientRepository.findOne(eq("1"))).thenReturn(mockPacient);

        //then
        Pacient pacient = pacientService.findById("1");

        //then
        assertNotNull(pacient);
        assertEquals(mockPacient, pacient);

        //verify mocks
        verify(pacientRepository).findOne(eq("1"));

    }

    @Test(expected = PacientNotFoundException.class)
    public void findById_should_throw_PacientNotFoundException_when_pacient_not_found() throws Exception {
        try {
            //expect
            when(pacientRepository.findOne(eq(PACIENT_ID))).thenReturn(null);

            //then
            pacientService.findById(PACIENT_ID);

        } finally {
            //verify mocks
            verify(pacientRepository).findOne(eq(PACIENT_ID));
        }
    }

    @Test
    public void update_should_return_updated_pacient() throws Exception {
        //given
        Pacient mockPacient = mockPacient("update");

        //expect
        when(pacientRepository.findOne(eq(PACIENT_ID))).thenReturn(mockPacient);

        //when
        Pacient pacient = pacientService.update(PACIENT_ID, 0, CP, JMENA, PRIJMENI, EMAIL, MOBIL);

        //then
        assertNotNull(pacient);
        assertEquals(mockPacient.getId(), pacient.getId());
        assertEquals(CP, pacient.getCp());
        assertEquals(JMENA, pacient.getJmena());
        assertEquals(PRIJMENI, pacient.getPrijmeni());
        assertNotNull(pacient.getKontakt());
        assertEquals(EMAIL, pacient.getKontakt().getEmail());
        assertEquals(MOBIL, pacient.getKontakt().getMobil());

        //verify mocks
        verify(pacientRepository).findOne(eq("1"));
        verify(pacientRepository).update(eq(mockPacient));
    }

    @Test(expected = PacientNotFoundException.class)
    public void update_should_throw_pacient_not_found_exception_when_id_not_found() {
        //expect
        when(pacientRepository.findOne(eq(PACIENT_ID))).thenReturn(null);
        try {
            //when
            pacientService.update(PACIENT_ID, 0, CP, JMENA, PRIJMENI, EMAIL, MOBIL);
        } finally {
            //verify mocks
            verify(pacientRepository).findOne(eq(PACIENT_ID));
        }
    }

    @Test
    public void update_nastaveni_uctu_ok() {
        //given
        NastaveniUctu mockNastaveni = mockNastaveniUctu();

        //expect
        when(nastaveniUctuRepository.findByPacientId(eq(PACIENT_ID))).thenReturn(mockNastaveni);

        //when
        NastaveniUctu nastaveniUctu = pacientService.updateNastaveniUctu(PACIENT_ID, MOBILNI_APLIKACE, 2, PREDEPSANE_VYDANE, KARTA_PACIENTA);

        //then
        assertEquals(MOBILNI_APLIKACE, nastaveniUctu.getPreferovanaKomunikace());
        assertEquals((Integer) 2, nastaveniUctu.getDobaUchovani());
        assertEquals(PREDEPSANE_VYDANE, nastaveniUctu.getPristupNaIdentifikatory());
        assertEquals(KARTA_PACIENTA, nastaveniUctu.getZpusobPristupu());

        //verify mocks
        verify(nastaveniUctuRepository).findByPacientId(eq(PACIENT_ID));
    }

    @Test(expected = PacientNotFoundException.class)
    public void update_nastaveni_uctu_throws_pacient_not_found_exception_when_given_user_not_found() {
        //expect
        when(nastaveniUctuRepository.findByPacientId(eq(PACIENT_ID))).thenReturn(null);

        try {
            //when
            pacientService.updateNastaveniUctu(PACIENT_ID, MOBILNI_APLIKACE, 2, PREDEPSANE_VYDANE, KARTA_PACIENTA);
        } finally {
            //verify mocks
            verify(nastaveniUctuRepository).findByPacientId(eq(PACIENT_ID));
        }
    }

    /* Tovarny */

    private NastaveniUctu mockNastaveniUctu() {
        return new NastaveniUctu(MOBILNI_APLIKACE, 1, PREDEPSANE_VYDANE, KARTA_PACIENTA);
    }

    private static Pacient mockPacient(String prefix) {
        return PacientFactory.createNewPacient(
                prefix + CP,
                prefix + JMENA,
                prefix + PRIJMENI,
                prefix + EMAIL,
                prefix + MOBIL,
                prefix + HESLO
        );
    }
}