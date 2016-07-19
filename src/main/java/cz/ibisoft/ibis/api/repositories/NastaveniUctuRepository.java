package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Richard Stefanca
 */
@Repository
public interface NastaveniUctuRepository extends CrudRepository<NastaveniUctu, String> {

    @Nullable
    NastaveniUctu findByPacientId(String pacientId);
}
