package cz.ibisoft.ibis.api;

import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.domain.PacientFactory;
import cz.ibisoft.ibis.api.repositories.PacientRepository;
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
    ApplicationRunner commandLineRunner(PacientRepository pacientRepository) {
        return args -> {
            Pacient pacient = PacientFactory.createNewPacient("0000000001", "Test", "Testovic", "testovic@test.cz", "555444333");
            pacientRepository.save(pacient);
        };
    }

}
