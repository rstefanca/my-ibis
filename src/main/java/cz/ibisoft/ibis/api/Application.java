package cz.ibisoft.ibis.api;

import cz.ibisoft.ibis.api.domain.Patient;
import cz.ibisoft.ibis.api.domain.PacientFactory;
import cz.ibisoft.ibis.api.domain.PatientCard;
import cz.ibisoft.ibis.api.repositories.PatientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Richard Stefanca
 */

@SpringBootApplication
@SuppressWarnings("unused")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            Patient patient = PacientFactory.createNewPacient("0000000001", "Test", "Testovic", "testovic@test.cz", "555444333", "heslo");
            PatientCard patientCard = PatientCard.createPatientCard(patient, "1234560", "Test");
            patient.addPatientCard(patientCard);
            patientRepository.save(patient);
            System.out.println("Patient " + patient.getId() + " created.");
        };
    }

}
