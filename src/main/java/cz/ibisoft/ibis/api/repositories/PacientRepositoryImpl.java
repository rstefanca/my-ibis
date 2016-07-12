package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author Richard Stefanca
 */

@Repository
public class PacientRepositoryImpl implements PacientRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void update(Pacient pacient) {
        em.detach(pacient);
        em.merge(pacient);
        em.flush();
    }
}
