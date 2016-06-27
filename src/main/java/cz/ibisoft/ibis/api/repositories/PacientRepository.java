package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.model.Pacient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Richard Stefanca
 */

@Repository
public interface PacientRepository extends CrudRepository<Pacient, String> {
    Optional<Pacient> findOnById(String id);
}
