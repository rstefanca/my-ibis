package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.Kontakt;
import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.repositories.NastaveniUctuRepository;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
import cz.ibisoft.ibis.api.services.exceptions.PacientNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Richard Stefanca
 */

@RunWith(MockitoJUnitRunner.class)
public class JpaPacientServiceTest {

    @Mock
    private PacientRepository pacientRepository;

    @Mock
    private NastaveniUctuRepository nastaveniUctuRepository;

    @InjectMocks
    private PacientService pacientService = new JpaPacientService();

    @Test //todo rename
    public void create() throws Exception {

        Pacient pacient = pacientService.create("cp", "jmena", "prijmeni", "email", "mobil");

        assertNotNull(pacient);
        assertEquals("cp", pacient.getCp());
        assertEquals("jmena", pacient.getJmena());
        assertEquals("prijmeni", pacient.getPrijmeni());
        assertNotNull(pacient.getKontakt());
        assertEquals("email", pacient.getKontakt().getEmail());
        assertEquals("mobil", pacient.getKontakt().getMobil());
        assertEquals((Long)0L, pacient.getVersion());

        verify(pacientRepository).update(any(Pacient.class));
    }

    @Test
    public void findById() throws Exception {
        Pacient mockPacient = mockPacient("findById");

        when(pacientRepository.findOne(eq("1"))).thenReturn(mockPacient);

        Pacient pacient = pacientService.findById("1");

        assertNotNull(pacient);
        assertEquals(mockPacient, pacient);

        verify(pacientRepository).findOne(eq("1"));

    }

    @Test(expected = PacientNotFoundException.class)
    public void findById_throws_PacientNotFoundException() throws Exception {
        try {
            when(pacientRepository.findOne(eq("1"))).thenReturn(null);

            pacientService.findById("1");

        } finally {
            verify(pacientRepository).findOne(eq("1"));
        }
    }

    @Test
    public void update() throws Exception {
        Pacient mockPacient = mockPacient("update");

        when(pacientRepository.findOne(eq("1"))).thenReturn(mockPacient);

        Pacient pacient = pacientService.update("1", 0, "cp", "jmena", "prijmeni", "email", "mobil");

        assertNotNull(pacient);
        assertEquals(mockPacient.getId(), pacient.getId());
        assertEquals("cp", pacient.getCp());
        assertEquals("jmena", pacient.getJmena());
        assertEquals("prijmeni", pacient.getPrijmeni());
        assertNotNull(pacient.getKontakt());
        assertEquals("email", pacient.getKontakt().getEmail());
        assertEquals("mobil", pacient.getKontakt().getMobil());


        verify(pacientRepository).findOne(eq("1"));
        verify(pacientRepository).update(eq(mockPacient));
    }

    @Test(expected = PacientNotFoundException.class)
    public void update_throws_pacient_not_found_exception_when_id_not_found() {
        when(pacientRepository.findOne(eq("1"))).thenReturn(null);
        try {
            pacientService.update("1", 0, "cp", "jmena", "prijmeni", "email", "mobil");
        } finally {
            verify(pacientRepository).findOne(eq("1"));
        }
    }

    @Test
    public void update_nastaveni_uctu_ok() {
        NastaveniUctu mockNastaveni = mockNastaveniUctu();
        String pacientId = UUID.randomUUID().toString();

        when(nastaveniUctuRepository.findByPacientId(eq(pacientId))).thenReturn(mockNastaveni);

        NastaveniUctu nastaveniUctu = pacientService.updateNastaveniUctu(pacientId, "MOBILNI_APLIKACE", 2, "PREDEPSANE_VYDANE", "KARTA_PACIENTA");

        assertEquals("MOBILNI_APLIKACE", nastaveniUctu.getPreferovanaKomunikace());
        assertEquals((Integer)2, nastaveniUctu.getDobaUchovani());
        assertEquals("PREDEPSANE_VYDANE", nastaveniUctu.getPristupNaIdentifikatory());
        assertEquals("KARTA_PACIENTA", nastaveniUctu.getZpusobPristupu());

        verify(nastaveniUctuRepository).findByPacientId(eq(pacientId));
    }

    @Test(expected = PacientNotFoundException.class)
    public void update_nastaveni_uctu_throws_pacient_not_found_exception_when_given_user_not_found() {
        String pacientId = UUID.randomUUID().toString();
        when(nastaveniUctuRepository.findByPacientId(eq(pacientId))).thenReturn(null);

        try {
            pacientService.updateNastaveniUctu(pacientId, "MOBILNI_APLIKACE", 2, "PREDEPSANE_VYDANE", "KARTA_PACIENTA");
        } finally {
            verify(nastaveniUctuRepository).findByPacientId(eq(pacientId));
        }

    }

    private NastaveniUctu mockNastaveniUctu() {
        return new NastaveniUctu("SMS", 1, "PREDEPSANE", "OTP_KOD");
    }

    private static Pacient mockPacient(String prefix) {
        return new Pacient(
                prefix + "cp",
                prefix + "jmena",
                prefix + "prijmeni",
                new Kontakt(
                        prefix+ "email",
                        prefix+ "mobil"
                )
        );
    }

}