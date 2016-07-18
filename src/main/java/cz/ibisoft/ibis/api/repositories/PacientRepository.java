package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.Pacient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Richard Stefanca
 */

@Repository
public interface PacientRepository extends CrudRepository<Pacient, String>, PacientRepositoryCustom {

    Optional<Pacient> findByIdAndVersion(String guid, String version);

    Optional<Pacient> findByKontaktEmail(String userName);
}
