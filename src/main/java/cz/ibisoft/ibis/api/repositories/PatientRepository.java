package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Richard Stefanca
 */

@Repository
public interface PatientRepository extends CrudRepository<Patient, String>, PatientRepositoryCustom {

    Optional<Patient> findByIdAndVersion(String guid, String version);

    Optional<Patient> findByContactEmail(String userName);
}
