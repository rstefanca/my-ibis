package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author Richard Stefanca
 */

@Repository
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void update(Patient patient) {
        em.detach(patient);
        em.merge(patient);
        em.flush();
    }
}
