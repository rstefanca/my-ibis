package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.Kontakt;
import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
import cz.ibisoft.ibis.api.services.exceptions.PacientNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
    public void update_throws_exception_when_id_not_found() {
        when(pacientRepository.findOne(eq("1"))).thenReturn(null);
        try {
            pacientService.update("1", 0, "cp", "jmena", "prijmeni", "email", "mobil");
        } finally {
            verify(pacientRepository).findOne(eq("1"));
        }
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