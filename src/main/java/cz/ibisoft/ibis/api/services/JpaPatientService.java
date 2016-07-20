package cz.ibisoft.ibis.api.services;

import cz.ibisoft.ibis.api.domain.*;
import cz.ibisoft.ibis.api.repositories.AccountSettingRepository;
import cz.ibisoft.ibis.api.repositories.PatientRepository;
import cz.ibisoft.ibis.api.services.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Implementace {@link PatientService}
 *
 * @author Richard Stefanca
 */
@Service
public class JpaPatientService implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AccountSettingRepository accountSettingRepository;

    /**
     * Zalozeni pacienta
     *
     * @param cp
     * @param jmena
     * @param prijmeni
     * @param email
     * @param mobile
     * @param heslo
     */
    @Override
    @Transactional
    public Patient create(String cp, String jmena, String prijmeni, String email, String mobile, String heslo) {
        Patient patient = PacientFactory.createNewPacient(cp, jmena, prijmeni, email, mobile, heslo);
        patientRepository.save(patient);
        return patient;
    }

    /**
     * Nacteni daneho pacienta
     *
     * @param id id pacienta
     * @return pacient
     * @throws PatientNotFoundException pokud neni pacient nalezen
     */
    @Override
    public Patient findById(String id) {
        Objects.requireNonNull(id, "Parameter id cannot be null");
        Patient patient = patientRepository.findOne(id);
        if (patient == null) {
            throw new PatientNotFoundException(id);
        }

        return patient;
    }

    @Override
    public Patient update(String id, long version, String cp, String jmena, String prijmeni, String email, String mobile) {
        Patient patient = findById(id);
        patient.setVersion(version);
        patient.setInsuranceNumber(cp);
        patient.setFirstName(jmena);
        patient.setLastName(prijmeni);
        patient.getContact().setEmail(email);
        patient.getContact().setMobil(mobile);
        patientRepository.update(patient);
        return patient;
    }

    @Override
    @Transactional
    public AccountSetting updateAccountSettings(String guid, PreferredCommunication preferredCommunication, Integer dobaUchovani, PristupNaIdentifikatory pristupNaIdentifikatory, AccessType accessType) {
        AccountSetting accountSetting = loadAccountSettings(guid);
        accountSetting.setPreferredCommunication(preferredCommunication);
        accountSetting.setDobaUchovani(dobaUchovani);
        accountSetting.setPristupNaIdentifikatory(pristupNaIdentifikatory);
        accountSetting.setAccessType(accessType);
        accountSettingRepository.save(accountSetting); //asi update jako pacient, kvuli version

        return accountSetting;
    }

    @Override
    public AccountSetting loadAccountSettings(String guid) {
        AccountSetting accountSetting =  accountSettingRepository.findByPatientId(guid);
        if (accountSetting == null) {
            throw new PatientNotFoundException(guid);
        }

        return accountSetting;
    }
}
