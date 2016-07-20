package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.PatientCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Richard Stefanca
 */

@Repository
public interface PatientCardRepository extends CrudRepository<PatientCard, String> {

    List<PatientCard> findCardsByPatientId(String patientId);

}
