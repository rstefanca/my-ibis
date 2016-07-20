package cz.ibisoft.ibis.api.repositories;

import cz.ibisoft.ibis.api.domain.AccountSetting;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Richard Stefanca
 */
@Repository
public interface AccountSettingRepository extends CrudRepository<AccountSetting, String> {

    @Nullable
    AccountSetting findByPatientId(String patientId);
}
